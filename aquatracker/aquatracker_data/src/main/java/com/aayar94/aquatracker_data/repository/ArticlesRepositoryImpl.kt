package com.aayar94.aquatracker_data.repository

import com.aayar94.aquatracker_data.remote.ArticlesApi
import com.aayar94.aquatracker_domain.model.Articles
import com.aayar94.aquatracker_domain.repository.ArticleRepository

class ArticlesRepositoryImpl(
    val api: ArticlesApi
) : ArticleRepository {

    override suspend fun getArticles(): Articles {
        return api.getArticles()
    }


}