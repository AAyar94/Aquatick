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

        val baseFactor = when (gender) {
            is Gender.Male -> (35 * weight)
            is Gender.Female -> (30 * weight)
            is Gender.LGBTQ -> (33 * weight)
        }

        val ageFactor = when {
            age < 18 -> 1.1
            age > 50 -> 0.9
            else -> 1.0
        }

        val genderFactor = when (gender) {
            is Gender.Male -> 1.1
            is Gender.Female -> 1.0
            is Gender.LGBTQ -> 1.05
        }

        val activityFactor = when (activityLevel) {
            is ActivityLevel.NotActive -> {
                0.75
            }

            is ActivityLevel.LessThanAverage -> {
                0.8
            }

            is ActivityLevel.Average -> {
                0.85
            }

            is ActivityLevel.Active -> {
                0.9
            }

            is ActivityLevel.VeryActive -> {
                0.95
            }
        }

        /**     OLD Formula
        /*val preFactor = when (gender) {
        // (30 mL/kg x 60 kg) + (0.0333 mL/kg x 60 kg/yıl x 30 yaş)
        is Gender.Male -> ((35 / 1000) * weight) + ((0.0333) * age * weight)
        is Gender.Female -> ((30/ 1000) * weight) + ((0.0333) * age * weight)
        else -> ((33/1000) * weight) + ((0.0333) * age * weight)
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
        }*/
        }*/
        val formattedValue =
            (baseFactor * ageFactor * genderFactor * activityFactor).roundToInt()
        return when {
            formattedValue < 2500 -> 2500
            formattedValue > 3500 -> 3500
            else -> formattedValue
        }
    }
}