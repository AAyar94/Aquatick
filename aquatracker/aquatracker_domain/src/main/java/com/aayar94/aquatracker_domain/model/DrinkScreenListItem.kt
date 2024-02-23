package com.aayar94.aquatracker_domain.model

import java.time.LocalDateTime

data class DrinkScreenListItem(
    val drinkType: DrinkType,
    val defaultAmount: Int,
    val drinkIcon: Int,
    val localDate: LocalDateTime = LocalDateTime.now()
)