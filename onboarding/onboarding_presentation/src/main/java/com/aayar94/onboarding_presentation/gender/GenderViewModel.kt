package com.aayar94.onboarding_presentation.gender

import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.core.domain.model.Gender
import com.aayar94.core.domain.preferences.Preferences
import com.aayar94.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenderViewModel @Inject constructor(
    val preferences: Preferences
) : ViewModel() {

    var genderState by mutableStateOf<Gender>(Gender.Male)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun genderChange(gender: Gender) {
        genderState = gender
    }

    fun onNextClick() {
        viewModelScope.launch {
            preferences.saveGender(genderState)
            _uiEvent.send(UiEvent.Success)
        }
    }

}