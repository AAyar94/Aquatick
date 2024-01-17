package com.aayar94.onboarding_domain.usecase

import com.aayar94.core.domain.preferences.Preferences

class GetUserAgeUseCase(
    private val preferences: Preferences
) {
    operator fun invoke(): Int {
        return preferences.getUserInfo().age
    }
}