package com.aayar94.aquatracker_presentation.drink

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.aayar94.aquatracker_domain.DrinkTypesWithIconUseCase
import com.aayar94.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class DrinkViewModel @Inject constructor(
     drinkTypesWithIconUseCase: DrinkTypesWithIconUseCase
) : ViewModel() {

    var drinkUIState by mutableStateOf(DrinkUIState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        drinkUIState.list = drinkTypesWithIconUseCase.invoke()
    }
}