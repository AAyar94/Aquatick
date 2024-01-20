package com.aayar94.aquatracker_presentation.drink

import com.aayar94.aquatracker_domain.usecase.ScreenDrinkItem

data class LastDrinksState(
    var list: List<ScreenDrinkItem> = emptyList()
)