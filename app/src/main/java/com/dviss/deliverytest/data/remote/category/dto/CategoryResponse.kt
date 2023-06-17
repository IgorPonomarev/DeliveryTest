package com.dviss.deliverytest.data.remote.category.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse(
    @SerialName("сategories")
    val categories: List<CategoryItem>
)