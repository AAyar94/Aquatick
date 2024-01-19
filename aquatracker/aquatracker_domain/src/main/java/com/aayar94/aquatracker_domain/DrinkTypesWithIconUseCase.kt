package com.aayar94.aquatracker_domain

import com.aayar94.aquatracker_domain.model.DrinkType
import com.aayar94.aquatracker_domain.util.getIcon

class DrinkTypesWithIconUseCase {
    operator fun invoke(): List<ScreenDrinkItem> {
        return listOf(
        ScreenDrinkItem(DrinkType.GlassOfWater,DrinkType.GlassOfWater.getIcon(DrinkType.GlassOfWater)),
        ScreenDrinkItem(DrinkType.BottleOfWater,DrinkType.BottleOfWater.getIcon(DrinkType.BottleOfWater)),
        ScreenDrinkItem(DrinkType.ACupOfCoffee,DrinkType.ACupOfCoffee.getIcon(DrinkType.ACupOfCoffee)),
        ScreenDrinkItem(DrinkType.Tea,DrinkType.Tea.getIcon(DrinkType.Tea)),
        ScreenDrinkItem(DrinkType.HerbalTea,DrinkType.HerbalTea.getIcon(DrinkType.HerbalTea)),
        ScreenDrinkItem(DrinkType.CarbonatedDrinks,DrinkType.CarbonatedDrinks.getIcon(DrinkType.CarbonatedDrinks)),
        ScreenDrinkItem(DrinkType.Milk,DrinkType.Milk.getIcon(DrinkType.Milk)),
        ScreenDrinkItem(DrinkType.Smoothie,DrinkType.Smoothie.getIcon(DrinkType.Smoothie)),
        ScreenDrinkItem(DrinkType.FruitJuice,DrinkType.FruitJuice.getIcon(DrinkType.FruitJuice)))
    }


}

data class ScreenDrinkItem(
    val drinkType: DrinkType,
    val drinkIcon: Int
)