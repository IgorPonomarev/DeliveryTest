package com.dviss.deliverytest.data.local.mapper

import com.dviss.deliverytest.data.local.entity.CategoryEntity
import com.dviss.deliverytest.domain.model.Category

fun CategoryEntity.toCategory(): Category {
    return Category(
        id = id,
        name = name,
        imageUrl = imageUrl
    )
}

fun Category.toCategoryEntity(): CategoryEntity {
    return CategoryEntity(
        id = id,
        name = name,
        imageUrl = imageUrl
    )
}