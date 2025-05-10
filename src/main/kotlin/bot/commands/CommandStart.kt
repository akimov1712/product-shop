package bot.commands

import com.github.kotlintelegrambot.dispatcher.Dispatcher
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.InlineKeyboardMarkup
import com.github.kotlintelegrambot.entities.keyboard.InlineKeyboardButton

fun Dispatcher.commandStart() {
    command("start") {
        val chatId = message.chat.id

        bot.sendMessage(
            chatId = ChatId.fromId(chatId),
            text = "👋 Добро пожаловать! Выберите, куда хотите перейти:",
            replyMarkup = InlineKeyboardMarkup.create(
                listOf(
                    InlineKeyboardButton.CallbackData(
                        text = "\uD83D\uDECD\uFE0F Магазин продуктов",
                        callbackData = "shop?page=1"
                    ),
                    InlineKeyboardButton.CallbackData(
                        text = "\uD83D\uDCC2 Категории продуктов",
                        callbackData = "open_categories"
                    ),
                    InlineKeyboardButton.CallbackData(
                        text = "\uD83D\uDED2 Корзина",
                        callbackData = "open_cart"
                    )
                )
            )
        )
    }
}