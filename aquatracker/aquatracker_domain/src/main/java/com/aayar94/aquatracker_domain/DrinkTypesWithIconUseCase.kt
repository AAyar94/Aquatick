package com.aayar94.aquatracker_domain

import com.aayar94.aquatracker_domain.model.DrinkType
import com.aayar94.aquatracker_domain.util.getIcon

class DrinkTypesWithIconUseCase {
    operator fun invoke(): List<ScreenDrinkItem> {
        return listOf(
            ScreenDrinkItem(
                DrinkType.Glass_Of_Water,
                DrinkType.Glass_Of_Water.getIcon(DrinkType.Glass_Of_Water)
            ),
            ScreenDrinkItem(
                DrinkType.Bottle_Of_Water,
                DrinkType.Bottle_Of_Water.getIcon(DrinkType.Bottle_Of_Water)
            ),
            ScreenDrinkItem(
                DrinkType.A_Cup_Of_Coffee,
                DrinkType.A_Cup_Of_Coffee.getIcon(DrinkType.A_Cup_Of_Coffee)
            ),
            ScreenDrinkItem(DrinkType.Tea, DrinkType.Tea.getIcon(DrinkType.Tea)),
            ScreenDrinkItem(DrinkType.Herbal_Tea, DrinkType.Herbal_Tea.getIcon(DrinkType.Herbal_Tea)),
            ScreenDrinkItem(
                DrinkType.Carbonated_Drinks,
                DrinkType.Carbonated_Drinks.getIcon(DrinkType.Carbonated_Drinks)
            ),
            ScreenDrinkItem(DrinkType.Milk, DrinkType.Milk.getIcon(DrinkType.Milk)),
            ScreenDrinkItem(DrinkType.Smoothie, DrinkType.Smoothie.getIcon(DrinkType.Smoothie)),
            ScreenDrinkItem(
                DrinkType.Fruit_Juice,
                DrinkType.Fruit_Juice.getIcon(DrinkType.Fruit_Juice)
            )
        )
    }


}

data class ScreenDrinkItem(
    val drinkType: DrinkType,
    val drinkIcon: Int
)