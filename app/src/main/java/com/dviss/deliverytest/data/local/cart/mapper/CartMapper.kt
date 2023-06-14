package com.dviss.deliverytest.data.local.cart.mapper

import com.dviss.deliverytest.data.local.cart.entity.CartEntity
import com.dviss.deliverytest.domain.model.CartItem

fun CartEntity.toCartItem(): CartItem {
    return CartItem(
        id = id,
        number = number
    )
}

fun CartItem.toCartEntity(): CartEntity {
    return CartEntity(
        id = id,
        number = number
    )
}