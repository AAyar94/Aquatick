package com.aayar94.settings_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.core.domain.preferences.Preferences
import com.aayar94.core.util.UiEvent
import com.aayar94.settings_domain.use_case.DropDatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferences: Preferences,
    private val dropDatabaseUseCase: DropDatabaseUseCase
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

    fun deleteEverythingAndCloseApp() {
        viewModelScope.launch(Dispatchers.IO) {
            preferences.deleteSharesPreferences()
            dropDatabaseUseCase.invoke()
            _uiEvent.send(UiEvent.Success)
        }
    }
}