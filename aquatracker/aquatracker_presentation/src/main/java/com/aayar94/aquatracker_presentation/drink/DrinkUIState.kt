package com.aayar94.aquatracker_presentation.drink

import com.aayar94.aquatracker_domain.DrinkTypesWithIconUseCase
import com.aayar94.aquatracker_domain.ScreenDrinkItem

data class DrinkUIState(
    var list: List<ScreenDrinkItem> = emptyList()
)