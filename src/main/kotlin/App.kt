import plugins.DatabasePlugin

fun main() {
    configurePlugins()
}

private fun configurePlugins(){
    DatabasePlugin.init()
}