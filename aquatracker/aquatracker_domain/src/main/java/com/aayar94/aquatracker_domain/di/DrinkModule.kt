package com.aayar94.aquatracker_domain.di

import com.aayar94.aquatracker_domain.DrinkTypesWithIconUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DrinkModule {

    @Provides
    @Singleton
    fun provideDrinkTypesListUseCase(): DrinkTypesWithIconUseCase {
        return DrinkTypesWithIconUseCase()
    }
}