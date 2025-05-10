package tables

import entities.ProductEntity
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.javatime.time
import org.jetbrains.exposed.sql.transactions.transaction
import tables.Products.categoryId
import tables.Products.createdAt
import tables.Products.image
import tables.Products.price
import tables.Products.updatedAt
import java.time.LocalTime

object Products: IntIdTable("products") {

    val name = varchar("name", 255)
    val categoryId = reference("category_id", Categories.id, onDelete = ReferenceOption.SET_NULL).nullable()
    val image = varchar("image", 255).nullable()
    val price = integer("price")
    val createdAt = time("created_at")
    val updatedAt = time("updated_at")

    fun add(name: String, categoryId: Int?, image: String, price: Int) = transaction {
        Products.insertAndGetId {
            it[Products.name] = name
            it[Products.categoryId] = categoryId
            it[Products.image] = image
            it[Products.price] = price
            it[createdAt] = LocalTime.now()
            it[updatedAt] = LocalTime.now()
        }.value
    }

    fun update(id: Int, name: String, categoryId: Int?, image: String, price: Int, ) = transaction {
        val oldProducts = select(id) ?: return@transaction null
        Products.upsert {
            it[Products.id] = id
            it[Products.name] = name
            it[Products.categoryId] = categoryId
            it[Products.image] = image
            it[Products.price] = price
            it[Products.createdAt] = oldProducts.createdAt
            it[updatedAt] = LocalTime.now()
        }
    }

    fun delete(id: Int) = transaction { Products.deleteWhere { Products.id eq id } }

    fun select(id: Int) = transaction { Products.selectAll().where { Products.id eq id }.singleOrNull() }?.toProductEntity()

    fun select(offset: Int, limit: Int) = transaction {
        Products.selectAll().offset(offset.toLong()).limit(limit).map { it.toProductEntity() }
    }

    private fun ResultRow.toProductEntity() = ProductEntity(
        id = this[Products.id].value,
        name = this[name],
        category = this[categoryId]?.value?.let { Categories.select(it) },
        image = this[image],
        price = this[price],
        createdAt = this[createdAt],
        updatedAt = this[updatedAt],
    )

}