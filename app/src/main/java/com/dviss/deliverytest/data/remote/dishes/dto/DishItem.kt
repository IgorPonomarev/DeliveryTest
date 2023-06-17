package com.dviss.deliverytest.data.remote.dishes.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DishItem(
    val id: Int,
    val name: String,
    val price: Double,
    val weight: Double,
    val description: String,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("tegs")
    val tags: List<String>
)
