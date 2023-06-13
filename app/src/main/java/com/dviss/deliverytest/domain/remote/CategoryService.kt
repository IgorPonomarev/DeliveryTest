package com.dviss.deliverytest.domain.remote

import com.dviss.deliverytest.domain.model.Category

interface CategoryService {
    suspend fun getCategories(): List<Category>
}