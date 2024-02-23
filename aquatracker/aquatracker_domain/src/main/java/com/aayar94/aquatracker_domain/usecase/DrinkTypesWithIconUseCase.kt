package com.aayar94.aquatracker_domain.usecase

import com.aayar94.aquatracker_domain.model.DrinkScreenListItem
import com.aayar94.aquatracker_domain.model.DrinkType
import com.aayar94.aquatracker_domain.util.getIcon

class DrinkTypesWithIconUseCase {
    operator fun invoke(): List<DrinkScreenListItem> {
        return listOf(
            DrinkScreenListItem(
                drinkType = DrinkType.Glass_Of_Water,
                defaultAmount = 200,
                drinkIcon = DrinkType.Glass_Of_Water.getIcon(DrinkType.Glass_Of_Water)
            ),
            DrinkScreenListItem(
                DrinkType.Bottle_Of_Water, defaultAmount = 500,
                DrinkType.Bottle_Of_Water.getIcon(DrinkType.Bottle_Of_Water)
            ),
            DrinkScreenListItem(
                DrinkType.A_Cup_Of_Coffee, defaultAmount = 200,
                DrinkType.A_Cup_Of_Coffee.getIcon(DrinkType.A_Cup_Of_Coffee)
            ),
            DrinkScreenListItem(
                DrinkType.Tea, defaultAmount = 100,
                DrinkType.Tea.getIcon(DrinkType.Tea)
            ),
            DrinkScreenListItem(
                DrinkType.Herbal_Tea, defaultAmount = 150,
                DrinkType.Herbal_Tea.getIcon(DrinkType.Herbal_Tea)
            ),
            DrinkScreenListItem(
                DrinkType.Carbonated_Drinks, defaultAmount = 200,
                DrinkType.Carbonated_Drinks.getIcon(DrinkType.Carbonated_Drinks)
            ),
            DrinkScreenListItem(
                DrinkType.Milk, defaultAmount = 200, DrinkType.Milk.getIcon(DrinkType.Milk)
            ),
            DrinkScreenListItem(
                DrinkType.Smoothie, defaultAmount = 300,
                DrinkType.Smoothie.getIcon(DrinkType.Smoothie)
            ),
            DrinkScreenListItem(
                DrinkType.Fruit_Juice, defaultAmount = 250,
                DrinkType.Fruit_Juice.getIcon(DrinkType.Fruit_Juice)
            )
        )
    }


}