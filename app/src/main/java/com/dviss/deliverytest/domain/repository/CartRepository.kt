package com.dviss.deliverytest.domain.repository

import com.dviss.deliverytest.domain.model.CartItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun getCartItems(): Flow<List<CartItem>>

    suspend fun updateItemNumber(item: CartItem, number: Int)

    suspend fun addItem(item: CartItem)

    suspend fun deleteItem(item: CartItem)
}