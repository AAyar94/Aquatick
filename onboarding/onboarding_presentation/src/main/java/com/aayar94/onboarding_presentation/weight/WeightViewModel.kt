package com.aayar94.onboarding_presentation.weight

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.core.util.UiEvent
import com.aayar94.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightViewModel @Inject constructor(
    private val preferences: com.aayar94.core.domain.preferences.Preferences
) : ViewModel() {

    var weightState by mutableFloatStateOf(80.0f)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun weightChange(weight: Float) {
        weightState = weight
    }

    fun onNextClick() {
        viewModelScope.launch {
            kotlin.run {
                if (weightState == 0f) {
                    _uiEvent.send(
                        UiEvent.ShowSnackbar(
                            UiText.StringResource(com.aayar94.core.R.string.weight_cannot_be_empty)
                        )
                    )
                    return@run
                }
                preferences.saveWeight(weightState)
                _uiEvent.send(UiEvent.Success)
            }
        }
    }
}