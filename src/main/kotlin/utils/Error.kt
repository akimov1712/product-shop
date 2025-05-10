package utils

fun <T>errorHandler(onError: () -> Unit = {}, func: () -> T): T?{
    return try {
        func()
    } catch (e: Exception) {
        onError()
        println("Произошла ошибка: ${e.message}")
        null
    }
}