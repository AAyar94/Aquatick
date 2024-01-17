package com.aayar94.onboarding_presentation.daily_intake_calculation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.core.domain.preferences.Preferences
import com.aayar94.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyIntakeCalculationViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {

    var itemVisibilityState by mutableStateOf(DailyIntakeCalculationUIState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun startAnim() {
        viewModelScope.launch {
            itemVisibilityState = itemVisibilityState.copy(firstItemVisibility = true)
            delay(1000)
            itemVisibilityState = itemVisibilityState.copy(secondItemVisibility = true)
            delay(1000)
            itemVisibilityState = itemVisibilityState.copy(thirdItemVisibility = true)
            delay(1000)
            itemVisibilityState = itemVisibilityState.copy(fourthItemVisibility = true)
            delay(1000)
            itemVisibilityState = itemVisibilityState.copy(fifthItemVisibility = true)
            delay(1000)
            itemVisibilityState = itemVisibilityState.copy(dailyIntakeAmount = "2250")
        }
    }

    fun onFinishedClick() {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.Success)
        }
        //TODO Usecase calculation
    }
}