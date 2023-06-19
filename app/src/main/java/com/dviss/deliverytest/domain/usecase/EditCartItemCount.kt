package com.dviss.deliverytest.domain.usecase

import com.dviss.deliverytest.domain.model.CartItem
import com.dviss.deliverytest.domain.repository.CartRepository

class EditCartItemCount(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(item: CartItem, number: Int) {
        if (number == 0) {
            cartRepository.deleteItem(item)
        } else {
            cartRepository.updateItemNumber(item, number)
        }
    }
}