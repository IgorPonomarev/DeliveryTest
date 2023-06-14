package com.dviss.deliverytest.data.remote.dishes.mapper

import com.dviss.deliverytest.data.remote.dishes.dto.DishItem
import com.dviss.deliverytest.domain.model.Dish

fun DishItem.toDish(): Dish {
    return Dish(
        id = id,
        name = name,
        price = price,
        weight = weight,
        description = description,
        imageUrl = imageUrl,
        tags = tags
    )
}