package com.dviss.deliverytest.data.repository

import com.dviss.deliverytest.data.local.cart.CartDao
import com.dviss.deliverytest.data.local.cart.entity.CartEntity
import com.dviss.deliverytest.data.local.cart.mapper.toCartEntity
import com.dviss.deliverytest.data.local.cart.mapper.toCartItem
import com.dviss.deliverytest.domain.model.CartItem
import com.dviss.deliverytest.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CartRepositoryImpl(private val cartDao: CartDao): CartRepository {
    override suspend fun getCartItems(): Flow<List<CartItem>> {
        return cartDao.getCartItems().map { cartEntities ->
            cartEntities.map {
                it.toCartItem()
            }
        }
    }

    override suspend fun updateItemNumber(item: CartItem, number: Int) {
        cartDao.updateItem(CartEntity(item.id, number))
    }

    override suspend fun addItem(item: CartItem) {
        cartDao.addItem(item.toCartEntity())
    }

    override suspend fun deleteItem(item: CartItem) {
        cartDao.deleteItem(item.toCartEntity())
    }
}