package entities

import java.time.LocalTime

data class ProductEntity(
    val id: Int,
    val name: String,
    val category: CategoryEntity?,
    val image: String?,
    val price: Int,
    val createdAt: LocalTime,
    val updatedAt: LocalTime,
)
