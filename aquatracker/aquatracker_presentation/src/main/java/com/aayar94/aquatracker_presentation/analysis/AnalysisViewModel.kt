package com.aayar94.aquatracker_presentation.analysis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.aquatracker_domain.usecase.GetDrinkForDateUseCase
import com.aayar94.aquatracker_domain.usecase.GetLastWeekDailyIntake
import com.patrykandpatrick.vico.core.entry.entryModelOf
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AnalysisViewModel @Inject constructor(
    private val getLastWeekDailyIntake: GetLastWeekDailyIntake,
    private val getDrinkForDateUseCase: GetDrinkForDateUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AnalysisUIState())
    val uiState = _uiState.asStateFlow()


    fun getDrinksForDate(localDate: LocalDate) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getDrinkForDateUseCase.invoke(localDate)
            response.let { drinksList ->
                _uiState.update {
                    it.copy(
                        drinkList = drinksList.reversed()
                    )
                }
            }
        }
    }

    fun getLastWeekDailyIntake() {
        viewModelScope.launch {
            val now = LocalDateTime.now()
            val startDate = if (now.dayOfMonth < 7) {
                LocalDateTime.of(
                    now.year, now.monthValue, 1, now.hour, now.minute, now.second
                )
            } else {
                now.minusDays(7)
            }
            viewModelScope.launch {
                val result = getLastWeekDailyIntake.invoke(
                    now.year,
                    now.monthValue,
                    now.dayOfMonth,
                    startDate.year,
                    startDate.monthValue,
                    startDate.dayOfMonth
                )
                val entities = result.map {
                    Pair(it.dayOfMonth, it.totalAmount)
                }
                _uiState.update {
                    it.copy(
                        chartList = entryModelOf(*entities.toTypedArray())
                    )
                }
            }
        }
    }

    fun onPreviousDayClick() {
        _uiState.update {
            it.copy(
                dateState = _uiState.value.dateState.minusDays(1)
            )
        }
        getDrinksForDate(uiState.value.dateState)
    }

    fun onNextDayClick() {
        _uiState.update {
            it.copy(
                dateState = _uiState.value.dateState.plusDays(1)
            )
        }
        getDrinksForDate(uiState.value.dateState)
    }

    fun onDeleteItem(deleteItemId: Int) {

    }
}