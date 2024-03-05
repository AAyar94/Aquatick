package com.aayar94.aquatracker_presentation.home.component

import com.aayar94.aquatracker_domain.model.DrinkType
import com.aayar94.aquatracker_domain.model.ScreenDrinkItem
import java.time.LocalDateTime
import com.aayar94.core.R.drawable as AppDrawable

object MostUsedDrinksList {
    val mostUsedDrinksList = listOf(
        ScreenDrinkItem(
            drinkType = DrinkType.Glass_Of_Water,
            defaultAmount = 200,
            drinkIcon = AppDrawable.ic_drink_glass_of_water,
            localDate = LocalDateTime.now()
        ),
        ScreenDrinkItem(
            drinkType = DrinkType.Tea,
            defaultAmount = 100,
            drinkIcon = AppDrawable.ic_drink_tea,
            localDate = LocalDateTime.now()
        ),
        ScreenDrinkItem(
            drinkType = DrinkType.A_Cup_Of_Coffee,
            defaultAmount = 200,
            drinkIcon = AppDrawable.ic_drink_a_cup_of_coffee,
            localDate = LocalDateTime.now()
        ),
    )
}