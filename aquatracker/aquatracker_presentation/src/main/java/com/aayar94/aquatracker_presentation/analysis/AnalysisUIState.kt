package com.aayar94.aquatracker_presentation.analysis

import com.patrykandpatrick.vico.core.entry.FloatEntry

data class AnalysisUIState(
    val chartModel: List<FloatEntry> = emptyList()
)