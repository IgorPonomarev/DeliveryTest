package com.dviss.deliverytest.data.remote.category.dto

import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse(
    val categories: List<CategoryItem>
)