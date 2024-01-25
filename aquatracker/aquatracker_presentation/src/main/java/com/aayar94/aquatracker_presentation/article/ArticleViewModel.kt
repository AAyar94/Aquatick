package com.aayar94.aquatracker_presentation.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.aquatracker_domain.usecase.GetSingleArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val getSingleArticleUseCase: GetSingleArticleUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ArticleUIState())
    val uiState = _uiState.asStateFlow()


    fun getArticle(articleId: Int) {
        viewModelScope.launch {
            val response = getSingleArticleUseCase.invoke(articleId)
            _uiState.update {
                it.copy(
                    article = response,
                    isLoading = false
                )
            }
        }
    }
}