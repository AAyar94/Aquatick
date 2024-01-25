package com.aayar94.aquatracker_domain.di

import com.aayar94.aquatracker_domain.repository.ArticleRepository
import com.aayar94.aquatracker_domain.usecase.GetArticlesUseCase
import com.aayar94.aquatracker_domain.usecase.GetSingleArticleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ArticlesModule {


    @Provides
    @Singleton
    fun provideGetArticlesUseCase(
        repository: ArticleRepository
    ): GetArticlesUseCase {
        return GetArticlesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetSingleArticleUseCase(
        getArticlesUseCase: GetArticlesUseCase
    ): GetSingleArticleUseCase {
        return GetSingleArticleUseCase(getArticlesUseCase)
    }
}