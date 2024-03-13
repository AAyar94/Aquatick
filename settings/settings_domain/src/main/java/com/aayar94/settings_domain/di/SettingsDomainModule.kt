package com.aayar94.settings_domain.di

import com.aayar94.aquatracker_domain.repository.AquaTrackerRepository
import com.aayar94.core_ui.util.DataStoreRepository
import com.aayar94.settings_domain.use_case.DropDatabaseUseCase
import com.aayar94.settings_domain.use_case.ReadSelectedColorSchemeUseCase
import com.aayar94.settings_domain.use_case.ReadSystemThemePreferenceUseCase
import com.aayar94.settings_domain.use_case.SelectColorSchemeUseCase
import com.aayar94.settings_domain.use_case.UseSystemThemeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsDomainModule {

    @Provides
    @Singleton
    fun provideDropDatabaseModule(
        repository: AquaTrackerRepository
    ): DropDatabaseUseCase {
        return DropDatabaseUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUseSystemThemeUseCase(dataStoreRepository: DataStoreRepository): UseSystemThemeUseCase {
        return UseSystemThemeUseCase(dataStoreRepository)
    }

    @Provides
    @Singleton
    fun provideColorSchemeSelectorUseCase(dataStoreRepository: DataStoreRepository): SelectColorSchemeUseCase {
        return SelectColorSchemeUseCase(dataStoreRepository)
    }

    @Provides
    @Singleton
    fun provideReadSystemThemeUseCase(dataStoreRepository: DataStoreRepository): ReadSystemThemePreferenceUseCase {
        return ReadSystemThemePreferenceUseCase(dataStoreRepository)
    }

    @Provides
    @Singleton
    fun provideReadColorSchemeUseCase(dataStoreRepository: DataStoreRepository): ReadSelectedColorSchemeUseCase {
        return ReadSelectedColorSchemeUseCase(dataStoreRepository)
    }
}