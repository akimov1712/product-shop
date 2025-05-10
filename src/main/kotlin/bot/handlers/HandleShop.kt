package bot.handlers

import bot.Handle
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.InlineKeyboardMarkup
import com.github.kotlintelegrambot.entities.keyboard.InlineKeyboardButton
import controller.ProductController
import utils.Const

fun Handle.handleShop(chatId: Long?, page: Int = 1) {
    val controller = ProductController()
    val size = controller.getSizeProducts() ?: return
    val products = controller.selectProducts(offset = (page - 1) * Const.LIMIT_PRODUCTS, limit = Const.LIMIT_PRODUCTS) ?: return
    val paginationButtons = mutableListOf<InlineKeyboardButton>().apply {
        if (page > 1) {
            add(
                InlineKeyboardButton.CallbackData(
                    text = "Предыдущая страница",
                    callbackData = "shop?page=${page - 1}"
                )
            )
        }
        if (page * Const.LIMIT_PRODUCTS < size) {
            add(
                InlineKeyboardButton.CallbackData(
                    text = "Следующая страница",
                    callbackData = "shop?page=${page + 1}"
                )
            )
        }
    }
    val productButtons = products.map {
        InlineKeyboardButton.CallbackData(
            text = it.name,
            callbackData = "product?id=${it.id}"
        )
    }.chunked(2)
    val buttons = mutableListOf<List<InlineKeyboardButton>>().apply {
        add(paginationButtons)
        addAll(productButtons)
    }
    chatId?.let {
        bot.sendMessage(
            chatId = ChatId.fromId(it),
            text = "🛍 Вы перешли в раздел магазина продуктов. Здесь будут товары...",
            replyMarkup = InlineKeyboardMarkup.create(buttons)
        )
    }
}