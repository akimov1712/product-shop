package tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

object Cart: IntIdTable("cart") {

    val productId = reference("product_id", Products.id, onDelete = ReferenceOption.CASCADE)
    val userId = text("user_id")

    fun add(productId: Int, userId: String) = transaction {
        Cart.insert {
            it[Cart.productId] = productId
            it[Cart.userId] = userId
        }
    }

    fun update(id: Int, productId: Int, userId: String) = transaction {
        Cart.upsert {
            it[Cart.id] = id
            it[Cart.productId] = productId
            it[Cart.userId] = userId
        }
    }

    fun deleteWithUserId(userId: String) = transaction {
        Cart.deleteWhere { Cart.userId eq userId }
    }

    fun delete(id: Int) = transaction { Cart.deleteWhere { Cart.productId eq id } }

    fun select(userId: String) = transaction { Cart.selectAll().where(Cart.userId eq userId).toList() }.toProductList()

    private fun List<ResultRow>.toProductList() = this.map { Products.select(it[Products.id].value) }.filterNotNull()

}