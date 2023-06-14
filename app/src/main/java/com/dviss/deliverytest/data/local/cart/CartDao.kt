package com.dviss.deliverytest.data.local.cart

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dviss.deliverytest.data.local.cart.entity.CartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(item: CartEntity)

    @Update
    suspend fun updateItem(cartEntity: CartEntity)

    @Query("SELECT * FROM cart")
    fun getCartItems(): Flow<List<CartEntity>>

    @Delete
    suspend fun deleteItem(cartEntity: CartEntity)
}