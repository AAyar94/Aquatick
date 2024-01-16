package com.aayar94.onboarding_presentation.name

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.core.domain.preferences.Preferences
import com.aayar94.core.util.UiEvent
import com.aayar94.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NameViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {

    var nameState by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun nameChange(name: String) {
        nameState = name
    }

    fun onNextClick() {
        viewModelScope.launch {
            if (nameState.isBlank()) {
                kotlin.run {
                    _uiEvent.send(
                        UiEvent.ShowSnackbar(
                            UiText.StringResource(com.aayar94.core.R.string.name_cannot_be_empty)
                        )
                    )
                    return@run
                }
                preferences.saveName(nameState)
                _uiEvent.send(UiEvent.Success)
            }
        }
    }
}