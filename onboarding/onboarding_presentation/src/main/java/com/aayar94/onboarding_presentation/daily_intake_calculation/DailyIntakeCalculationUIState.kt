package com.aayar94.onboarding_presentation.daily_intake_calculation

data class DailyIntakeCalculationUIState(
    val firstItemVisibility: Boolean? = false,
    val secondItemVisibility: Boolean? = false,
    val thirdItemVisibility: Boolean? = false,
    val fourthItemVisibility: Boolean? = false,
    val fifthItemVisibility: Boolean? = false,
    val sixthItemVisibility: Boolean = false,
    val seventhItemVisibility: Boolean = false,
    val dailyIntakeAmount: String? = null

)