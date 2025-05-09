package plugins

import tables.Cart
import tables.Categories
import tables.Products
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import utils.Env

object DatabasePlugin {

    private fun connectDatabase(){
        Database.connect(
            url = Env["DB_URL"],
            driver = Env["DB_DRIVER"],
            user = Env["DB_USER"],
            password = Env["DB_PASSWORD"]
        )
    }

    private fun loadTables(){
        transaction {
            SchemaUtils.create(Products, Categories, Cart)
        }
    }

    fun init(){
        connectDatabase()
        loadTables()
    }

}