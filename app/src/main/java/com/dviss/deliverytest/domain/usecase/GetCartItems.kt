package com.dviss.deliverytest.domain.usecase

import com.dviss.deliverytest.domain.repository.CartRepository

class GetCartItems(
    private val repository: CartRepository
) {
    suspend operator fun invoke() = repository.getCartItems()
}