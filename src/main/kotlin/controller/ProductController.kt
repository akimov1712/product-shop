package controller

import tables.Products
import tables.Products.createdAt
import utils.errorHandler
import java.time.LocalTime

class ProductController {

    fun selectProduct(id: Int) = errorHandler {
        Products.select(id).also { println("Успешно получен рецепт с id $id: $it") }
    }

    fun selectProducts(offset: Int, limit: Int = 20) = errorHandler {
        Products.select(offset, limit).also { println("Успешно получено $limit продуктов: $it") }
    }

    fun updateProduct(id: Int, name: String, categoryId: Int?, image: String, price: Int) = errorHandler{
        Products.update(id, name, categoryId, image, price).also { println("Успешно обновлен продукт с id: $it") }
    }

    fun addProduct(name: String, categoryId: Int?, image: String, price: Int) = errorHandler{
        Products.add(name, categoryId, image, price).also { println("Успешно добавлен продукт с id: $it") }
    }

    fun deleteProduct(id: Int) = errorHandler{
        Products.delete(id).also { println("Успешно удален продукт с id: $id") }
    }

}