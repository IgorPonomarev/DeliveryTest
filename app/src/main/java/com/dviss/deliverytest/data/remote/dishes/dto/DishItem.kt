package com.dviss.deliverytest.data.remote.dishes.dto

import kotlinx.serialization.Serializable

@Serializable
data class DishItem(
    val id: Int,
    val name: String,
    val price: Double,
    val weight: Double,
    val description: String,
    val imageUrl: String,
    val tags: List<String>
)
