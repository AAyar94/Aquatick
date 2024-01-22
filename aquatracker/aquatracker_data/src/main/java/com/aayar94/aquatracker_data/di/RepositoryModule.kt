package com.aayar94.aquatracker_data.di

import com.aayar94.aquatracker_data.local.DrinkDao
import com.aayar94.aquatracker_data.remote.ArticlesApi
import com.aayar94.aquatracker_data.repository.AquaTrackerRepositoryImpl
import com.aayar94.aquatracker_data.repository.ArticlesRepositoryImpl
import com.aayar94.aquatracker_domain.repository.AquaTrackerRepository
import com.aayar94.aquatracker_domain.repository.ArticleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesDrinkRepository(dao: DrinkDao): AquaTrackerRepository {
        return AquaTrackerRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            ).build()
    }

    @Provides
    @Singleton
    fun provideArticlesApi(
        client: OkHttpClient
    ): ArticlesApi {
        return Retrofit.Builder()
            .baseUrl(ArticlesApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideArticlesRepository(api: ArticlesApi): ArticleRepository {
        return ArticlesRepositoryImpl(api)
    }
}