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

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @ViewModelScoped
    @Provides
    fun provideUseCases(
        @ApplicationContext appContext: Context,
        locationService: LocationService
    ): AppUseCases {
        return AppUseCases(
            getLocation = GetLocation(appContext, locationService)
        )
    }
}