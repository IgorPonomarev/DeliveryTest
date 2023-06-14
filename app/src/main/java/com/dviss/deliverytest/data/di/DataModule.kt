package com.dviss.deliverytest.data.di

import android.app.Application
import androidx.room.Room
import com.dviss.deliverytest.data.local.cart.CartDatabase
import com.dviss.deliverytest.data.local.catalog.FoodDatabase
import com.dviss.deliverytest.data.remote.category.CategoryServiceImpl
import com.dviss.deliverytest.data.remote.dishes.DishServiceImpl
import com.dviss.deliverytest.data.repository.CartRepositoryImpl
import com.dviss.deliverytest.data.repository.FoodRepositoryImpl
import com.dviss.deliverytest.domain.remote.CategoryService
import com.dviss.deliverytest.domain.remote.DishService
import com.dviss.deliverytest.domain.repository.CartRepository
import com.dviss.deliverytest.domain.repository.FoodRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    //=================================== Local ==========================================

    //Food Database
    @Provides
    @Singleton
    fun provideFoodDatabase(app: Application): FoodDatabase {
        return Room.databaseBuilder(
            app,
            FoodDatabase::class.java,
            "food_db"
        ).build()
    }

    //Cart Database
    @Provides
    @Singleton
    fun provideCartDatabase(app: Application): CartDatabase {
        return Room.databaseBuilder(
            app,
            CartDatabase::class.java,
            "cart_db"
        ).build()
    }


    //=================================== Remote ==========================================

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        val json = Json {
            ignoreUnknownKeys = true
        }
        return HttpClient(Android) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer(json)
            }
        }
    }

    @Provides
    @Singleton
    fun provideDishService(client: HttpClient): DishService {
        return DishServiceImpl(client)
    }

    @Provides
    @Singleton
    fun provideCategoryService(client: HttpClient): CategoryService {
        return CategoryServiceImpl(client)
    }
    
    
    //================================ Repository =======================================

    //Food repository
    @Provides
    @Singleton
    fun provideFoodRepository(
        db: FoodDatabase,
        dishService: DishService,
        categoryService: CategoryService
    ): FoodRepository {
        return FoodRepositoryImpl(db.dao, categoryService, dishService)
    }

    //Cart repository
    @Provides
    @Singleton
    fun provideCartRepository(
        db: CartDatabase
    ): CartRepository {
        return CartRepositoryImpl(db.dao)
    }
}