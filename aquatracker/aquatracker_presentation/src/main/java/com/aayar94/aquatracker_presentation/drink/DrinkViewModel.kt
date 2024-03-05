package com.aayar94.aquatracker_presentation.drink

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.aquatracker_domain.model.DrinkScreenListItem
import com.aayar94.aquatracker_domain.model.ScreenDrinkItem
import com.aayar94.aquatracker_domain.usecase.DrinkTypesWithIconUseCase
import com.aayar94.aquatracker_domain.usecase.SaveDrinkToDbUseCase
import com.aayar94.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkViewModel @Inject constructor(
    drinkTypesWithIconUseCase: DrinkTypesWithIconUseCase,
    private val saveDrinkToDbUseCase: SaveDrinkToDbUseCase
) : ViewModel() {

    var drinkUIState by mutableStateOf(DrinkUIState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onDrinkClick(drinkScreenListItem: DrinkScreenListItem) {
        viewModelScope.launch {
            saveDrinkToDbUseCase.invoke(
                ScreenDrinkItem(
                    drinkType = drinkScreenListItem.drinkType,
                    defaultAmount = drinkScreenListItem.defaultAmount,
                    drinkIcon = drinkScreenListItem.drinkIcon,
                    localDate = drinkScreenListItem.localDate
                )
            )
        }
    }

    init {
        drinkUIState.list = drinkTypesWithIconUseCase.invoke()
    }
}