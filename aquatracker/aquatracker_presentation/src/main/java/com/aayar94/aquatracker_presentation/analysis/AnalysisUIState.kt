package com.aayar94.aquatracker_presentation.analysis

import com.aayar94.aquatracker_domain.usecase.ScreenDrinkItem
import com.patrykandpatrick.vico.core.entry.ChartEntryModel
import java.time.LocalDate


data class AnalysisUIState(
    val isLoading: Boolean = true,
    val chartList: ChartEntryModel? = null,
    val defaultDailyIntakeAmount: Int? = null,
    var drinkList: List<ScreenDrinkItem> = emptyList(),
    val dateState: LocalDate = LocalDate.now()
)