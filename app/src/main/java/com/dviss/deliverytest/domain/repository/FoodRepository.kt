package com.dviss.deliverytest.domain.repository

import com.dviss.deliverytest.domain.model.Category
import com.dviss.deliverytest.domain.model.Dish
import kotlinx.coroutines.flow.Flow

interface FoodRepository {

    //dishes

    suspend fun downloadDishes()

    suspend fun saveDishes(dishes: List<Dish>)

    suspend fun getDishes(): Flow<List<Dish>>

    //categories

    suspend fun downloadCategories()

    suspend fun saveCategories(categories: List<Category>)

    suspend fun getCategories(): Flow<List<Category>>
}