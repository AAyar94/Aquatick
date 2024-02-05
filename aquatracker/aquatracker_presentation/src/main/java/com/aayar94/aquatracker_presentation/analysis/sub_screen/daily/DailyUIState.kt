package com.aayar94.aquatracker_presentation.analysis.sub_screen.daily

import com.patrykandpatrick.vico.core.entry.ChartEntryModel

data class DailyUIState(
    val isLoading: Boolean = true,
    val list: ChartEntryModel? = null
)