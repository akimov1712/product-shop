package controller

import tables.Categories
import utils.errorHandler

class CategoryController {

    fun selectCategory(id: Int) = errorHandler {
        Categories.select(id).also { println("Успешно получена категория с id $id: $it") }
    }

    fun selectCategories(offset: Int, limit: Int = 20) = errorHandler {
        Categories.select(offset, limit).also { println("Успешно получено $limit категорий: $it") }
    }

    fun updateCategory(id: Int, name: String) = errorHandler{
        Categories.update(id, name).also { println("Успешно обновлена категория с id: $it") }
    }

    fun addCategory(name: String) = errorHandler{
        Categories.add(name).also { println("Успешно добавлена категория с id: $it") }
    }

    fun deleteCategory(id: Int) = errorHandler{
        Categories.delete(id).also { println("Успешно удалена категория с id: $id") }
    }

}