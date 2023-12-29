package com.aayar94.aquatick.core.di

import android.content.Context
import androidx.room.Room
import com.aayar94.aquatick.data.local.user.UserDao
import com.aayar94.aquatick.data.local.user.UserDataDatabase
import com.aayar94.aquatick.util.Constant.USER_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): UserDataDatabase {
        return Room.databaseBuilder(
            context,
            UserDataDatabase::class.java,
            USER_DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideUserDataDao(database: UserDataDatabase): UserDao {
        return database.getUserDao()
    }


}