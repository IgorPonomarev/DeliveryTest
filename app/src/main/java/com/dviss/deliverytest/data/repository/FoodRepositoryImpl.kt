package com.dviss.deliverytest.data.repository

import com.dviss.deliverytest.data.local.FoodDao
import com.dviss.deliverytest.data.local.mapper.toCategory
import com.dviss.deliverytest.data.local.mapper.toCategoryEntity
import com.dviss.deliverytest.data.local.mapper.toDish
import com.dviss.deliverytest.data.local.mapper.toDishEntity
import com.dviss.deliverytest.domain.model.Category
import com.dviss.deliverytest.domain.model.Dish
import com.dviss.deliverytest.domain.remote.CategoryService
import com.dviss.deliverytest.domain.remote.DishService
import com.dviss.deliverytest.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FoodRepositoryImpl(
    private val foodDao: FoodDao,
    private val categoryService: CategoryService,
    private val dishService: DishService
) : FoodRepository {

    //dishes

    override suspend fun downloadDishes() {
        foodDao.saveDishes(dishService.getDishes().map { it.toDishEntity() })
    }

    override suspend fun saveDishes(dishes: List<Dish>) {
        foodDao.saveDishes(dishes.map { it.toDishEntity() })
    }

    override suspend fun getDishes(): Flow<List<Dish>> {
        return foodDao.getDishes().map { dishEntities ->
            dishEntities.map {
                it.toDish()
            }
        }
    }

    //categories

    override suspend fun downloadCategories() {
        foodDao.saveCategories(categoryService.getCategories().map { it.toCategoryEntity() })
    }

    override suspend fun saveCategories(categories: List<Category>) {
        foodDao.saveCategories(categories.map { it.toCategoryEntity() })
    }

    override suspend fun getCategories(): Flow<List<Category>> {
        return foodDao.getCategories().map { categoryEntities ->
            categoryEntities.map {
                it.toCategory()
            }
        }
    }
}