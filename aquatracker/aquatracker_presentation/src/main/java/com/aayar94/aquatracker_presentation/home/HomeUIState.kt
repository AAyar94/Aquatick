package com.aayar94.aquatracker_presentation.home

data class HomeUIState(
    val greetings: String? = null,
    val name: String? = null,
    val currentIntake: String? = null,
    val lastIntakeTime: String? = null,
    val lastIntakeType: String? = null
)