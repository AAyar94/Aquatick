package com.aayar94.aquatracker_domain.usecase

import com.aayar94.aquatracker_domain.model.DrinkType
import com.aayar94.aquatracker_domain.util.getIcon
import java.time.LocalDate

class DrinkTypesWithIconUseCase {
    operator fun invoke(): List<ScreenDrinkItem> {
        return listOf(
            ScreenDrinkItem(
                drinkType = DrinkType.Glass_Of_Water,
                defaultAmount = 200,
                drinkIcon = DrinkType.Glass_Of_Water.getIcon(DrinkType.Glass_Of_Water)
            ),
            ScreenDrinkItem(
                DrinkType.Bottle_Of_Water,
                500,
                DrinkType.Bottle_Of_Water.getIcon(DrinkType.Bottle_Of_Water)
            ),
            ScreenDrinkItem(
                DrinkType.A_Cup_Of_Coffee,
                200,
                DrinkType.A_Cup_Of_Coffee.getIcon(DrinkType.A_Cup_Of_Coffee)
            ),
            ScreenDrinkItem(
                DrinkType.Tea,
                100,
                DrinkType.Tea.getIcon(DrinkType.Tea)
            ),
            ScreenDrinkItem(
                DrinkType.Herbal_Tea,
                150,
                DrinkType.Herbal_Tea.getIcon(DrinkType.Herbal_Tea)
            ),
            ScreenDrinkItem(
                DrinkType.Carbonated_Drinks,
                200,
                DrinkType.Carbonated_Drinks.getIcon(DrinkType.Carbonated_Drinks)
            ),
            ScreenDrinkItem(DrinkType.Milk, 200, DrinkType.Milk.getIcon(DrinkType.Milk)),
            ScreenDrinkItem(
                DrinkType.Smoothie,
                300,
                DrinkType.Smoothie.getIcon(DrinkType.Smoothie)
            ),
            ScreenDrinkItem(
                DrinkType.Fruit_Juice,
                250,
                DrinkType.Fruit_Juice.getIcon(DrinkType.Fruit_Juice)
            )
        )
    }


}

data class ScreenDrinkItem(
    val drinkType: DrinkType,
    val defaultAmount: Int,
    val drinkIcon: Int,
    val localDate: LocalDate = LocalDate.now()
)