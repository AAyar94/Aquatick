package com.aayar94.aquatracker_presentation.analysis

import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.patrykandpatrick.vico.core.entry.entryModelOf

data class AnalysisUIState(
    val chartModel: List<FloatEntry> = emptyList()
)