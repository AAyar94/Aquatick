package com.aayar94.core.domain.model

data class UserInfo(
    val name: String,
    val gender: Gender,
    val age: Int,
    val weight: Float,
    val activityLevel: ActivityLevel,
    val dailyIntakeAmount: Int
)