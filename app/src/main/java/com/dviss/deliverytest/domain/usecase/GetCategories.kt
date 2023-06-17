package com.dviss.deliverytest.domain.usecase

import com.dviss.deliverytest.domain.repository.FoodRepository

class GetCategories(
    private val repository: FoodRepository
) {
    suspend operator fun invoke() = repository.getCategories()
}