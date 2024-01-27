package com.aayar94.aquatracker_presentation.analysis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.aquatracker_domain.usecase.AnalysisScreenChartCalculate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnalysisViewModel @Inject constructor(
    val analysisScreenChartCalculate: AnalysisScreenChartCalculate
) : ViewModel() {

    private val _uiState = MutableStateFlow(AnalysisUIState())
    val uiState = _uiState.asStateFlow()

    init {
        getAnalysis()
    }

    private fun getAnalysis() {
        viewModelScope.launch {
            val response = analysisScreenChartCalculate.invoke()

        }
    }

}