package com.aayar94.aquatracker_domain.model

import java.time.LocalDateTime

data class ChartModel(
    val date: LocalDateTime,
    val amount: Int
)