package com.aayar94.aquatracker_domain.usecase

import com.aayar94.aquatracker_domain.model.Article

class GetSingleArticleUseCase(
    private val getArticlesUseCase: GetArticlesUseCase
) {
    suspend operator fun invoke(id: Int): Article? {
        val articles = getArticlesUseCase.invoke()
        articles.articles.forEach { article ->
            if (id == article.id) {
                return article
            }
        }
        return null
    }
}