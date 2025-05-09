package utils

import io.github.cdimascio.dotenv.Dotenv

object Env {

    private val ENV =  Dotenv.load()

    operator fun get(key: String) = ENV[key] ?: throw NoSuchElementException("Value with key: $key not found")

}