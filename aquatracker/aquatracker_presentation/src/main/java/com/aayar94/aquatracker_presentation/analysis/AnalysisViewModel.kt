package com.aayar94.aquatracker_presentation.analysis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnalysisViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(AnalysisUIState())
    val uiState = _uiState.asStateFlow()

    fun updateTabState(value: Int) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    selectedTabState = value
                )
            }
        }
    }
}