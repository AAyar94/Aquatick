package com.aayar94.onboarding_presentation.age

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.core.domain.preferences.Preferences
import com.aayar94.core.util.UiEvent
import com.aayar94.core.util.UiText
import com.aayar94.onboarding_presentation.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgeViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {

    var ageState by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun ageChange(age: String) {
        ageState = age
    }

    fun onNextClick() {
        viewModelScope.launch {
            if (ageState.isNullOrEmpty()) {
                kotlin.run {
                    _uiEvent.send(
                        UiEvent.ShowSnackbar(
                            UiText.StringResource(com.aayar94.core.R.string.age_cannot_be_empty)
                        )
                    )
                    return@run
                }
                preferences.saveAge(ageState.toIntOrNull() ?: 0)
                _uiEvent.send(UiEvent.Success)
            }
        }
    }
}