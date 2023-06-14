package com.dviss.deliverytest.data.local.catalog.mapper

import com.dviss.deliverytest.data.local.catalog.entity.CategoryEntity
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