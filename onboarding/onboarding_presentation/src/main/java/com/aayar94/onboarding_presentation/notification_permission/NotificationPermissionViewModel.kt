package com.aayar94.onboarding_presentation.notification_permission

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
class NotificationPermissionViewModel @Inject constructor(
    val preferences: Preferences
) : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun onNextClicked() {
        viewModelScope.launch {
            preferences
            _uiEvent.send(UiEvent.Success)
        }
    }

    fun saveNotificationStatus(boolean: Boolean) {
        preferences.saveNotificationPermissionStatus(boolean)
    }


}