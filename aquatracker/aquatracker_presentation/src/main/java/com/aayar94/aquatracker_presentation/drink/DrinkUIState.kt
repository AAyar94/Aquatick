package com.aayar94.aquatracker_presentation.drink

import com.aayar94.aquatracker_domain.model.DrinkScreenListItem

data class DrinkUIState(
    var list: List<DrinkScreenListItem> = emptyList()
)