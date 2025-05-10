package tables

import entities.CategoryEntity
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

object Categories: IntIdTable("category") {

    val name = varchar("name", 255)

    fun add(name: String) = transaction {
        Categories.insertAndGetId { it[this.name] = name }
    }.value

    fun update(id: Int, name: String) = transaction {
        Categories.upsert{
            it[Categories.id] = id
            it[Categories.name] = name
        }
    }

    fun delete(id: Int) = transaction {
        Cart.deleteWhere { productId eq id }
    }

    fun selectWithCategory(categoryId: Int, offset: Int, limit: Int = 20) = transaction {
        Categories.selectAll().where(Categories.id eq categoryId).offset(offset.toLong()).limit(limit)
    }.map { it.toCategory() }

    fun select(id: Int) = transaction {
        Categories.selectAll().where(Categories.id eq id).singleOrNull()?.toCategory()
    }

    fun select(offset: Int, limit: Int) = transaction {
        Categories.selectAll().offset(offset.toLong()).limit(limit)
    }.map { it.toCategory() }

    private fun ResultRow.toCategory() = CategoryEntity(
        id = this[Categories.id].value,
        name = this[Categories.name],
    )

}