package com.aayar94.aquatracker_presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.prefs.Preferences
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    preferences: com.aayar94.core.domain.preferences.Preferences
) : ViewModel() {
    var homeState by mutableStateOf(HomeUIState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val userInfo = preferences.getUserInfo()
        viewModelScope.launch {
            homeState = homeState.copy(
                greetings = getGreeting(),
                name = userInfo.name,
                currentIntake = 250.toString(),
                lastIntakeTime = null,
                lastIntakeType = null
            )
        }
    }

    private fun getGreeting(): String {
        val currentHour = java.time.LocalTime.now().hour

        return when (currentHour) {
            in 5..11 -> "Good morning"
            in 12..16 -> "Good afternoon"
            in 17..20 -> "Good evening"
            else -> "Good night"
        }
    }

    fun onEnterDrinkClick() {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.Success)
        }
    }
}