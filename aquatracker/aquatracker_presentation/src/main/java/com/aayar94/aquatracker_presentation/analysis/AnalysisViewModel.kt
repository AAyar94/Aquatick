package com.aayar94.aquatracker_presentation.analysis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.aquatracker_domain.usecase.AnalysisScreenChartCalculate
import com.patrykandpatrick.vico.core.entry.entriesOf
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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

            val entries = entriesOf(2000, 3000, 1850, 1750, 1500)

            _uiState.update { it.copy(chartModel = entries) }
        }
    }

}