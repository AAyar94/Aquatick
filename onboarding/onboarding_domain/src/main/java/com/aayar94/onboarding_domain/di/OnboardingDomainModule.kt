package com.aayar94.onboarding_domain.di

import android.content.SharedPreferences
import com.aayar94.core.domain.preferences.Preferences
import com.aayar94.onboarding_domain.usecase.CalculateDailyIntakeAmountUseCase
import com.aayar94.onboarding_domain.usecase.GetUserActivityLevelUseCase
import com.aayar94.onboarding_domain.usecase.GetUserAgeUseCase
import com.aayar94.onboarding_domain.usecase.GetUserGenderUseCase
import com.aayar94.onboarding_domain.usecase.GetUserWeightUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OnboardingDomainModule {

    @Provides
    @Singleton
    fun provideGetUserAgeUseCase(
        preferences: Preferences
    ): GetUserAgeUseCase {
        return GetUserAgeUseCase(preferences)
    }

    @Provides
    @Singleton
    fun provideGetUserWeightUseCase(
        preferences: Preferences
    ): GetUserWeightUseCase {
        return GetUserWeightUseCase(preferences)
    }

    @Provides
    @Singleton
    fun provideGetUserGenderUseCase(preferences: Preferences): GetUserGenderUseCase {
        return GetUserGenderUseCase(preferences)
    }

    @Provides
    @Singleton
    fun provideGetUserActivityLevelUseCase(preferences: Preferences): GetUserActivityLevelUseCase {
        return GetUserActivityLevelUseCase(preferences)
    }

    @Provides
    @Singleton
    fun provideCalculateUserDailyIntakeUseCase(
        getUserAgeUseCase: GetUserAgeUseCase,
        getUserWeightUseCase: GetUserWeightUseCase,
        getUserGenderUseCase: GetUserGenderUseCase,
        getUserActivityLevelUseCase: GetUserActivityLevelUseCase
    ): CalculateDailyIntakeAmountUseCase {
        return CalculateDailyIntakeAmountUseCase(
            getUserGenderUseCase,
            getUserWeightUseCase,
            getUserAgeUseCase,
            getUserActivityLevelUseCase
        )
    }
}