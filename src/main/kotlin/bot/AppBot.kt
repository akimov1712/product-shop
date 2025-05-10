package bot

import bot.commands.commandStart
import bot.handlers.handleCategories
import bot.handlers.handleShop
import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.*
import com.github.kotlintelegrambot.dispatcher.handlers.CallbackQueryHandlerEnvironment
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.InlineKeyboardMarkup
import com.github.kotlintelegrambot.entities.keyboard.InlineKeyboardButton
import com.github.kotlintelegrambot.logging.LogLevel
import kotlinx.coroutines.Dispatchers
import utils.Env

typealias Handle = CallbackQueryHandlerEnvironment

object AppBot {

    fun createBot() = bot {
        token = Env["BOT_TOKEN"]
        logLevel = LogLevel.Error

        dispatch {
            dispatchCommands()
            dispatchQuery()
        }
    }

    private fun Dispatcher.dispatchCommands(){
        commandStart()
    }

    private fun Dispatcher.dispatchQuery(){
        callbackQuery {
            when {
                callbackQuery.data.contains("shop") -> handleShop(callbackQuery.message?.chat?.id)
                callbackQuery.data.contains("open_categories") -> handleCategories(callbackQuery.message?.chat?.id)
            }
        }
    }
}

