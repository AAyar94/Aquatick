package com.aayar94.settings_presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.core.domain.preferences.Preferences
import com.aayar94.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {

    private var _uiState = MutableStateFlow(SettingsUIState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    init {
        val state = preferences.readNotificationPermissionStates()
        _uiState.update {
            it.copy(isNotificationEnabled = state)
        }
    }

    fun onNotificationSwitch(boolean: Boolean) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isNotificationEnabled = boolean)
            }
        }
    }

    fun onDarkSwitch(boolean: Boolean) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isDarkThemeEnabled = boolean)
            }
        }
    }
}