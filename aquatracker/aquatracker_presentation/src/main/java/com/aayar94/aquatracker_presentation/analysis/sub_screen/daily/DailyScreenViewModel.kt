package com.aayar94.aquatracker_presentation.analysis.sub_screen.daily

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.aquatracker_domain.usecase.GetLastWeekDailyIntake
import com.patrykandpatrick.vico.core.entry.entryModelOf
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.OffsetDateTime
import javax.inject.Inject

@HiltViewModel
class DailyScreenViewModel @Inject constructor(
    private val getLastWeekDailyIntake: GetLastWeekDailyIntake
) : ViewModel() {

    private val _uiState = MutableStateFlow(DailyUIState())
    val uiState = _uiState.asStateFlow()


    fun getLastWeekDailyIntake() {
        val now = LocalDateTime.now()
        viewModelScope.launch {
            val response = getLastWeekDailyIntake.invoke(
                now.minusDays(7).toInstant(OffsetDateTime.now().offset).epochSecond.toInt(),
                now.dayOfMonth,
                now.monthValue,
                now.year
            )
            val dataPairs = response.map {
                Pair(it.date.dayOfMonth, it.amount)
            }
            _uiState.update {
                it.copy(
                    list = entryModelOf(*dataPairs.toTypedArray())
                )
            }
        }
    }
}