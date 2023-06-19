package com.dviss.deliverytest.domain.di

import com.dviss.deliverytest.domain.location.LocationService
import com.dviss.deliverytest.domain.usecase.AppUseCases
import com.dviss.deliverytest.domain.usecase.GetLocation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import android.content.Context
import com.dviss.deliverytest.domain.repository.CartRepository
import com.dviss.deliverytest.domain.repository.FoodRepository
import com.dviss.deliverytest.domain.usecase.AddDishToCart
import com.dviss.deliverytest.domain.usecase.DownloadCategories
import com.dviss.deliverytest.domain.usecase.DownloadDishes
import com.dviss.deliverytest.domain.usecase.EditCartItemCount
import com.dviss.deliverytest.domain.usecase.GetCartItems
import com.dviss.deliverytest.domain.usecase.GetCategories
import com.dviss.deliverytest.domain.usecase.GetDishes

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @ViewModelScoped
    @Provides
    fun provideUseCases(
        @ApplicationContext appContext: Context,
        locationService: LocationService,
        foodRepository: FoodRepository,
        cartRepository: CartRepository
    ): AppUseCases {
        return AppUseCases(
            getLocation = GetLocation(appContext, locationService),
            getCategories = GetCategories(foodRepository),
            downloadCategories = DownloadCategories(foodRepository),
            getDishes = GetDishes(foodRepository),
            downloadDishes = DownloadDishes(foodRepository),
            addDishToCart = AddDishToCart(cartRepository),
            getCartItems = GetCartItems(cartRepository),
            editCartItemCount = EditCartItemCount(cartRepository)
        )
    }
}