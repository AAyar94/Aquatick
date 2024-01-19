package com.aayar94.onboarding_presentation.night_time_picker

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.core.domain.preferences.Preferences
import com.aayar94.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NightTimeViewModel @Inject constructor(
    val preferences: Preferences
) : ViewModel() {

    var goingBedHour by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun timeChangedUpdate(time: String) {
        goingBedHour = time
    }

    fun onNextClicked() {
        viewModelScope.launch {
            preferences.saveBedTime(goingBedHour)
            _uiEvent.send(UiEvent.Success)
        }
    }

}