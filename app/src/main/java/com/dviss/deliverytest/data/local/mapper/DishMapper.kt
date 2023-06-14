package com.dviss.deliverytest.data.local.mapper

import com.dviss.deliverytest.data.local.entity.DishEntity
import com.dviss.deliverytest.domain.model.Dish

fun DishEntity.toDish(): Dish {
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

fun Dish.toDishEntity(): DishEntity {
    return DishEntity(
        id = id,
        name = name,
        price = price,
        weight = weight,
        description = description,
        imageUrl = imageUrl,
        tags = tags
    )
}