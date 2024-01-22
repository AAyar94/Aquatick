package com.aayar94.aquatracker_domain.usecase

import com.aayar94.aquatracker_domain.model.Articles
import com.aayar94.aquatracker_domain.repository.ArticleRepository

class GetArticlesUseCase(
    private val articlesRepository: ArticleRepository
) {

    suspend operator fun invoke(): Articles {
        return articlesRepository.getArticles()
    }

}