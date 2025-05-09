package plugins

import org.jetbrains.exposed.sql.Database
import utils.Env

object DatabasePlugin {

    fun connectDatabase(){
        Database.connect(
            url = Env["DB_URL"],
            driver = Env["DB_DRIVER"],
            user = Env["DB_USER"],
            password = Env["DB_PASSWORD"]
        )
    }

}