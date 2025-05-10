package controller

import tables.Cart
import tables.Cart.productId
import tables.Products
import tables.Products.createdAt
import utils.errorHandler
import java.time.LocalTime

class CartController {

    fun addToCart(productId: Int, userId: String) = errorHandler{
        Cart.add(productId, userId).also { println("Продукт id $productId был добавлен в корзину") }
    }

    fun deleteFromCart(id: Int) = errorHandler{
        Cart.delete(id).also { println("Продукт id $productId был удален из корзины") }
    }

    fun sendOrder(userId: String) = errorHandler{
        Cart.deleteWithUserId(userId).also { println("Заказ оформлен. Все продукты $userId удалены из корзины") }
    }

    fun selectCart(userId: String) = errorHandler{
        Cart.select(userId).also { println("Получены продукты из корзины: $it") }
    }

}