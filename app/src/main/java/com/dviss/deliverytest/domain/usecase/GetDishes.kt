package com.dviss.deliverytest.domain.usecase

import com.dviss.deliverytest.domain.repository.FoodRepository

class GetDishes(
    private val repository: FoodRepository
) {
    suspend operator fun invoke() = repository.getDishes()
}