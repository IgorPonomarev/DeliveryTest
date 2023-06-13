package com.dviss.deliverytest.domain.remote

import com.dviss.deliverytest.domain.model.Dish

interface DishService {
    suspend fun getDishes(): List<Dish>
}