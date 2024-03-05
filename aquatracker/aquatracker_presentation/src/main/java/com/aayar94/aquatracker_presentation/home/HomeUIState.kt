package com.aayar94.aquatracker_presentation.home

import com.aayar94.aquatracker_domain.model.ScreenDrinkItem
import com.aayar94.aquatracker_presentation.home.component.MostUsedDrinksList
import com.aayar94.core.domain.model.Gender

data class HomeUIState(
    val greetings: String? = null,
    val name: String? = null,
    val gender: Gender = Gender.Male,
    val currentIntake: String? = null,
    val lastIntakeTime: String? = null,
    val lastIntakeType: String? = null,
    val mostUsedDrinks: List<ScreenDrinkItem> = MostUsedDrinksList.mostUsedDrinksList
)