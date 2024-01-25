package com.aayar94.aquatracker_domain.util

import com.aayar94.aquatracker_domain.model.DrinkType
import com.aayar94.core.R.drawable as AppDrawable

fun DrinkType.getIcon(drinkType: DrinkType): Int {
    return when (drinkType) {
        is DrinkType.Glass_Of_Water -> AppDrawable.ic_drink_glass_of_water
        is DrinkType.Bottle_Of_Water -> AppDrawable.ic_drink_bottle_of_water
        is DrinkType.A_Cup_Of_Coffee -> AppDrawable.ic_drink_a_cup_of_coffe
        is DrinkType.Tea -> AppDrawable.ic_drink_tea
        is DrinkType.Herbal_Tea -> AppDrawable.ic_drink_herbaltea
        is DrinkType.Carbonated_Drinks -> AppDrawable.ic_drink_carbonated_drink
        is DrinkType.Milk -> AppDrawable.ic_drink_milk
        is DrinkType.Smoothie -> AppDrawable.ic_drink_smoothie
        is DrinkType.Fruit_Juice -> AppDrawable.ic_drink_fruit_juice
    }
}
