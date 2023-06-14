package com.dviss.deliverytest.data.local.cart

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dviss.deliverytest.data.local.cart.entity.CartEntity


@Database(
    entities = [
        CartEntity::class
    ],
    version = 1
)
abstract class CartDatabase: RoomDatabase() {
    abstract val dao: CartDao
}