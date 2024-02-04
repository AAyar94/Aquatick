package com.aayar94.aquatracker_presentation.analysis


data class AnalysisUIState(
    val selectedTabState: Int = 0,
    val chartModel: List<Float> = emptyList()
)