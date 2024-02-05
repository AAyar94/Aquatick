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
import okhttp3.internal.notify
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
        viewModelScope.launch {
            val now = LocalDateTime.now()
            val startDate = if (now.dayOfMonth < 7) {
                LocalDateTime.of(
                    now.year,
                    now.monthValue,
                    1,
                    now.hour,
                    now.minute,
                    now.second
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
                        list = entryModelOf(*entities.toTypedArray())
                    )
                }
            }
        }
    }
}