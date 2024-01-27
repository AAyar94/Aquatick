package com.aayar94.aquatracker_domain.di

import com.aayar94.aquatracker_domain.repository.AquaTrackerRepository
import com.aayar94.aquatracker_domain.usecase.AnalysisScreenChartCalculate
import com.aayar94.aquatracker_domain.usecase.CalculateTodaysIntakeUseCase
import com.aayar94.aquatracker_domain.usecase.DrinkTypesWithIconUseCase
import com.aayar94.aquatracker_domain.usecase.GetLastDrinksUseCase
import com.aayar94.aquatracker_domain.usecase.GetLastIntakeUseCase
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

    @Provides
    @Singleton
    fun providesCalculateTodaysIntake(
        repository: AquaTrackerRepository
    ): CalculateTodaysIntakeUseCase {
        return CalculateTodaysIntakeUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetLastIntakeUseCase(
        repository: AquaTrackerRepository
    ): GetLastIntakeUseCase {
        return GetLastIntakeUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAnalysisScreenChartsCalculateUseCase(): AnalysisScreenChartCalculate {
        return AnalysisScreenChartCalculate()
    }
}