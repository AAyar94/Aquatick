package com.aayar94.aquatracker_domain.util

import com.aayar94.aquatracker_domain.model.DrinkType
import com.aayar94.core.R.drawable as AppDrawable

fun DrinkType.getIcon(drinkType: DrinkType): Int {
    return when (drinkType) {
        is DrinkType.GlassOfWater -> AppDrawable.glass_of_water
        is DrinkType.BottleOfWater -> AppDrawable.ic_drink_bottle_water
        is DrinkType.ACupOfCoffee -> AppDrawable.drink_icon
        is DrinkType.Tea -> AppDrawable.ic_drink_tea
        is DrinkType.CarbonatedDrinks -> AppDrawable.ic_drink_carbonated_drinks
        is DrinkType.Milk -> AppDrawable.ic_drink_milk
        is DrinkType.Smoothie -> AppDrawable.ic_drink_milkshake
        is DrinkType.FruitJuice -> AppDrawable.ic_drink_fruit_juice
        else -> AppDrawable.glass_of_water
    }
}
