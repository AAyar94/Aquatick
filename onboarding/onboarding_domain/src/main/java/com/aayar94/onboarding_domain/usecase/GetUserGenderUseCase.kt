package com.aayar94.onboarding_domain.usecase

import com.aayar94.core.domain.model.Gender
import com.aayar94.core.domain.preferences.Preferences

class GetUserGenderUseCase(
    private val preferences: Preferences
) {
    operator fun invoke(): Gender {
        return preferences.getUserInfo().gender
    }
}