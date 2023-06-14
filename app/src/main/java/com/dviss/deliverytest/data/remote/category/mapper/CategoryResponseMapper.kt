package com.dviss.deliverytest.data.remote.category.mapper

import com.dviss.deliverytest.data.remote.category.dto.CategoryItem
import com.dviss.deliverytest.domain.model.Category

fun CategoryItem.toCategory(): Category {
    return Category(
        id = id,
        name = name,
        imageUrl = imageUrl
    )
}