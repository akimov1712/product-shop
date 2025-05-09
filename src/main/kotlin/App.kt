import org.jetbrains.exposed.sql.Database
import plugins.DatabasePlugin

fun main() {
    configurePlugins()
}

private fun configurePlugins(){
    DatabasePlugin.connectDatabase()
}