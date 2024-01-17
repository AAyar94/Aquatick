package com.aayar94.onboarding_domain.usecase

import java.util.prefs.Preferences

class GetUserWeightUseCase(
    private val preferences: com.aayar94.core.domain.preferences.Preferences
) {
    operator fun invoke(): Float {
        return preferences.getUserInfo().weight
    }
}