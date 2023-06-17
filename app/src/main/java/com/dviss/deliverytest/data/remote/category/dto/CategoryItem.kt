package com.dviss.deliverytest.data.remote.category.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryItem(
    val id: Int,
    val name: String,
    @SerialName("image_url")
    val imageUrl: String
)
