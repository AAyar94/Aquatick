package com.aayar94.aquatracker_presentation.home

import com.aayar94.core.domain.model.Gender

data class HomeUIState(
    val greetings: String? = null,
    val name: String? = null,
    val gender: Gender = Gender.Male,
    val currentIntake: String? = null,
    val lastIntakeTime: String? = null,
    val lastIntakeType: String? = null
)