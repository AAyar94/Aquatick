package com.aayar94.onboarding_presentation.daily_intake_calculation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.core.domain.preferences.Preferences
import com.aayar94.core.util.UiEvent
import com.aayar94.onboarding_domain.usecase.CalculateDailyIntakeAmountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyIntakeCalculationViewModel @Inject constructor(
    calculateDailyIntakeAmountUseCase: CalculateDailyIntakeAmountUseCase,
    val preferences: Preferences
) : ViewModel() {

    private val _uiState = MutableStateFlow(DailyIntakeCalculationUIState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val amount = calculateDailyIntakeAmountUseCase.invoke()

    fun startAnim() {
        viewModelScope.launch {
            _uiState.update { it.copy(firstItemVisibility = true) }
            delay(1000)
            _uiState.update { it.copy(secondItemVisibility = true) }
            delay(1000)
            _uiState.update { it.copy(thirdItemVisibility = true) }
            delay(1000)
            _uiState.update { it.copy(fourthItemVisibility = true) }
            delay(1000)
            _uiState.update { it.copy(firstItemVisibility = false, fifthItemVisibility = true) }
            delay(1000)
            _uiState.update { it.copy(secondItemVisibility = false, sixthItemVisibility = true) }
            delay(1000)
            _uiState.update {
                it.copy(
                    thirdItemVisibility = false,
                    seventhItemVisibility = true,
                    dailyIntakeAmount = amount.toString()
                )
            }
        }
    }

    fun onFinishedClick() {
        viewModelScope.launch {
            preferences.saveDailyIntakeAmount(amount = amount)
            preferences.saveOnboardingFinishedState(false)
            _uiEvent.send(UiEvent.Success)
        }
    }
}