package com.dviss.deliverytest.data.local.catalog

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dviss.deliverytest.data.local.catalog.entity.CategoryEntity
import com.dviss.deliverytest.data.local.catalog.entity.DishEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    //=================================== Categories =============================================

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCategories(categories: List<CategoryEntity>)

    @Query("SELECT * FROM categories")
    fun getCategories(): Flow<List<CategoryEntity>>

    //=================================== Dishes =================================================

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveDishes(dishes: List<DishEntity>)

    @Query("SELECT * FROM dishes")
    fun getDishes(): Flow<List<DishEntity>>
}