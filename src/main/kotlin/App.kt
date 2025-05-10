import bot.AppBot
import plugins.DatabasePlugin

fun main() {
    configurePlugins()
    startBot()
}

private fun startBot(){
    val bot = AppBot.createBot()
    bot.startPolling()
}

private fun configurePlugins(){
    DatabasePlugin.init()
}