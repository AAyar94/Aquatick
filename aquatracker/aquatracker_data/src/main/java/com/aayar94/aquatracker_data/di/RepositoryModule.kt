package com.aayar94.aquatracker_data.di

import com.aayar94.aquatracker_data.local.DrinkDao
import com.aayar94.aquatracker_data.repository.AquaTrackerRepositoryImpl
import com.aayar94.aquatracker_domain.repository.AquaTrackerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesRepository(dao: DrinkDao): AquaTrackerRepository {
        return AquaTrackerRepositoryImpl(dao)
    }

}