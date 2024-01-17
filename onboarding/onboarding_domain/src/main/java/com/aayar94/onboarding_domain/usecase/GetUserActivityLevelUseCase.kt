package com.aayar94.onboarding_domain.usecase

import com.aayar94.core.domain.model.ActivityLevel
import com.aayar94.core.domain.preferences.Preferences

class GetUserActivityLevelUseCase(
    private val preferences: Preferences
) {
    operator fun invoke(): ActivityLevel {
        return preferences.getUserInfo().activityLevel
    }
}