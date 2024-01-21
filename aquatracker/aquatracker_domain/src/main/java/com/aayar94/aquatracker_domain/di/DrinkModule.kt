package com.aayar94.aquatracker_domain.di

import com.aayar94.aquatracker_domain.repository.AquaTrackerRepository
import com.aayar94.aquatracker_domain.usecase.DrinkTypesWithIconUseCase
import com.aayar94.aquatracker_domain.usecase.GetLastDrinksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DrinkModule {

    @Provides
    @Singleton
    fun provideDrinkTypesListUseCase(): DrinkTypesWithIconUseCase {
        return DrinkTypesWithIconUseCase()
    }

    @Provides
    @Singleton
    fun provideGetLastDrinksUseCase(
        repository: AquaTrackerRepository
    ): GetLastDrinksUseCase {
        return GetLastDrinksUseCase(repository)
    }
}