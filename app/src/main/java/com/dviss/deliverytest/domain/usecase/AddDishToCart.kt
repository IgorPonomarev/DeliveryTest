package com.dviss.deliverytest.domain.usecase

import com.dviss.deliverytest.domain.model.CartItem
import com.dviss.deliverytest.domain.model.Dish
import com.dviss.deliverytest.domain.repository.CartRepository

class AddDishToCart(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(dish: Dish) {
        cartRepository.addItem(item = CartItem(dish.id, 1))
    }
}