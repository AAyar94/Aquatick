package com.aayar94.aquatracker_data.di

import android.app.Application
import androidx.room.Room
import com.aayar94.aquatracker_data.local.DrinkDao
import com.aayar94.aquatracker_data.local.DrinkDatabase
import com.aayar94.aquatracker_data.repository.AquaTrackerRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): DrinkDatabase {
        return Room.databaseBuilder(app, DrinkDatabase::class.java, "Drinks_DB").build()
    }

    @Provides
    @Singleton
    fun provideDao(db: DrinkDatabase): DrinkDao {
        return db.dao
    }

    @Provides
    @Singleton
    fun provideAquaTrackerRepository(dao: DrinkDao): AquaTrackerRepositoryImpl {
        return AquaTrackerRepositoryImpl(dao)
    }

}