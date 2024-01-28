package com.aayar94.settings_domain.di

import com.aayar94.aquatracker_domain.repository.AquaTrackerRepository
import com.aayar94.settings_domain.use_case.DropDatabaseUseCase
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

}