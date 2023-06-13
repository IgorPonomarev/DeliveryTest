package com.dviss.deliverytest.domain.model

data class Dish(
    val id: Int,
    val name: String,
    val price: Double,
    val weight: Double,
    val description: String,
    val imageUrl: String,
    val tags: List<String>
)
