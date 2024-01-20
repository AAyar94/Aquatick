package com.aayar94.aquatracker_data.mapper

import com.aayar94.aquatracker_data.local.entity.DrinkEntity
import com.aayar94.aquatracker_domain.usecase.ScreenDrinkItem
import com.aayar94.aquatracker_domain.model.DrinkType
import com.aayar94.aquatracker_domain.util.getIcon
import java.time.LocalDate

fun ScreenDrinkItem.toDrinkEntity(): DrinkEntity {
    return DrinkEntity(
        drinkType = drinkType.name,
        drinkAmount = defaultAmount,
        dayOfMonth = localDate.dayOfMonth,
        month = localDate.monthValue,
        year = localDate.year
    )
}


fun DrinkEntity.toScreenDrinkItem(): ScreenDrinkItem {
    return ScreenDrinkItem(
        drinkType = DrinkType.fromString(drinkType),
        defaultAmount = drinkAmount,
        localDate = LocalDate.of(year, month, dayOfMonth),
        drinkIcon = DrinkType.fromString(drinkType).getIcon(DrinkType.fromString(drinkType))
    )
}