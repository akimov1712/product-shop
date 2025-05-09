package tables

import entities.CategoryEntity
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

object Categories: IntIdTable("category") {

    val name = varchar("name", 255)

    fun add(name: String) = transaction {
        Categories.insert { it[this.name] = name }
    }

    fun update(id: Int, name: String) = transaction {
        Categories.upsert{
            it[Categories.id] = id
            it[Categories.name] = name
        }
    }

    fun delete(id: Int) = transaction { Cart.deleteWhere { Cart.productId eq id } }

    fun select(id: Int) = transaction { Categories.selectAll().where(Categories.id eq id).singleOrNull()?.toCategory() }

    fun select(offset: Int, limit: Int) = transaction {
        Categories.selectAll().offset(offset.toLong()).limit(limit)
    }.map { it.toCategory() }

    private fun ResultRow.toCategory() = CategoryEntity(
        id = this[Categories.id].value,
        name = this[Categories.name],
    )

}