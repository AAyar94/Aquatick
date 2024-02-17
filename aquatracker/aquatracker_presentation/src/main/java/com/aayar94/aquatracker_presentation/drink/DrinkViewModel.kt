package com.aayar94.aquatracker_presentation.drink

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.aquatracker_domain.repository.AquaTrackerRepository
import com.aayar94.aquatracker_domain.usecase.DrinkTypesWithIconUseCase
import com.aayar94.aquatracker_domain.usecase.GetLastDrinksUseCase
import com.aayar94.aquatracker_domain.usecase.ScreenDrinkItem
import com.aayar94.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkViewModel @Inject constructor(
    drinkTypesWithIconUseCase: DrinkTypesWithIconUseCase,
    private val getLastDrinksUseCase: GetLastDrinksUseCase,
    private val repository: AquaTrackerRepository
) : ViewModel() {

    var drinkUIState by mutableStateOf(DrinkUIState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onDrinkClick(screenDrinkItem: ScreenDrinkItem) {
        viewModelScope.launch {
            repository.insertDrink(screenDrinkItem)
        }
    }

    init {
        drinkUIState.list = drinkTypesWithIconUseCase.invoke()
    }
}