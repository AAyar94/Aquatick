package com.aayar94.onboarding_domain.usecase

import com.aayar94.core.domain.model.ActivityLevel
import com.aayar94.core.domain.model.Gender
import kotlin.math.roundToInt

class CalculateDailyIntakeAmountUseCase(
    getUserGenderUseCase: GetUserGenderUseCase,
    getUserWeightUseCase: GetUserWeightUseCase,
    getUserAgeUseCase: GetUserAgeUseCase,
    getUserActivityLevelUseCase: GetUserActivityLevelUseCase
) {
    private val gender = getUserGenderUseCase.invoke()
    private val weight = getUserWeightUseCase.invoke()
    private val age = getUserAgeUseCase.invoke()
    private val activityLevel = getUserActivityLevelUseCase.invoke()

    operator fun invoke(): Int {
        val preFactor = when (gender) {
            // (30 mL/kg x 60 kg) + (0.0333 mL/kg x 60 kg/yıl x 30 yaş)
            is Gender.Male -> (35 * weight) + (33.3 * age * weight)
            is Gender.Female -> (30 * weight) + (0.0333 * age * weight)
            else -> (33 * weight) + (0.0333 * age * weight)
        }

        val intakeFactor = when (activityLevel) {
            is ActivityLevel.NotActive -> {
                0.1
            }

            is ActivityLevel.LessThanAverage -> {
                0.2
            }

            is ActivityLevel.Average -> {
                0.3
            }

            is ActivityLevel.Active -> {
                0.4
            }

            is ActivityLevel.VeryActive -> {
                0.5
            }
        }

        return preFactor.roundToInt()+(preFactor * intakeFactor).roundToInt()
    }
}