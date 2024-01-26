package com.aayar94.aquatracker_data.mapper

import com.aayar94.aquatracker_data.local.entity.DrinkEntity
import com.aayar94.aquatracker_domain.usecase.ScreenDrinkItem
import com.aayar94.aquatracker_domain.model.DrinkType
import com.aayar94.aquatracker_domain.util.getIcon
import java.time.LocalDate
import java.time.LocalDateTime

fun ScreenDrinkItem.toDrinkEntity(): DrinkEntity {
    return DrinkEntity(
        drinkType = drinkType.name,
        drinkAmount = defaultAmount,
        hour = localDate.hour,
        minute = localDate.minute,
        second = localDate.second,
        dayOfMonth = localDate.dayOfMonth,
        month = localDate.monthValue,
        year = localDate.year
    )
}


fun DrinkEntity.toScreenDrinkItem(): ScreenDrinkItem {
    return ScreenDrinkItem(
        drinkType = DrinkType.fromString(drinkType),
        defaultAmount = drinkAmount,
        localDate = LocalDateTime.of(year, month, dayOfMonth, hour, minute, second),
        drinkIcon = DrinkType.fromString(drinkType).getIcon(DrinkType.fromString(drinkType))
    )
}