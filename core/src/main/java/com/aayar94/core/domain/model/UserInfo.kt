package com.aayar94.core.domain.model

import java.time.LocalTime

data class UserInfo(
    val name: String,
    val gender: Gender,
    val age: Int,
    val weight: Float,
    val activityLevel: ActivityLevel,
    val dailyIntakeAmount: Int,
    val getUpTimeHour:Int,
    val getUpTimeMinute:Int,
    val goingBedTimeHour:Int,
    val goingBedTimeMinute:Int
)