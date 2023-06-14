package com.dviss.deliverytest.data.remote.dishes.dto

import kotlinx.serialization.Serializable

@Serializable
data class DishResponse(
    val dishes: List<DishItem>
)
