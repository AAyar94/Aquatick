package com.aayar94.aquatracker_presentation.article

import com.aayar94.aquatracker_domain.model.Article

data class ArticleUIState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val article: Article? = null
)