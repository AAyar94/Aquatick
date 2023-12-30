package com.aayar94.aquatick.ui.screen.callculate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.aquatick.domain.usecase.GetUserDataUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CalculateViewModel @Inject constructor(
    private val getUserDataUserCase: GetUserDataUserCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CalculateUIState())
    val uiState = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            delay(1000)
            _uiState.value = _uiState.value.copy(stepOneVisibility = true)

            delay(1000)
            _uiState.value = _uiState.value.copy(stepTwoVisibility = true)

            delay(1000)
            _uiState.value = _uiState.value.copy(stepThreeVisibility = true)

            delay(1000)
            _uiState.value = _uiState.value.copy(stepFourVisibility = true)

            delay(1000)
            _uiState.value = _uiState.value.copy(stepFiveVisibility = true)
        }
    }

    fun calculateDailyIntakeAmount() {
        viewModelScope.launch {
            val data = getUserDataUserCase.invoke()
            _uiState.update {
                it.copy(
                    calculatedIntakeAmount = calculateDailyWaterIntake(
                        data.gender!!,
                        data.weight!!,
                        data.age!!,
                        data.activityLevel!!
                    )
                )
            }
        }
    }

    private fun calculateDailyWaterIntake(
        gender: String,
        weightKg: Double,
        age: Int,
        activityLevel: String
    ) :String {
        val activityFactor = when (activityLevel) {
            "Very Active" -> 0.7
            "Active" -> 0.6
            "Average" -> 0.5
            "Less than average" -> 0.4
            "Not Active" -> 0.3
            else -> 0.0
        }
        val ageFactor = when {
            age in 1..3 -> 0.9
            age in 4..8 -> 0.8
            age in 9..13 -> 0.7
            age in 14..18 -> when (gender.lowercase(Locale.ROOT)) {
                "male" -> 0.6
                "female" -> 0.6
                else -> 0.0
            }

            else -> {
                0.0
            }
        }

        return when (gender.lowercase(Locale.ROOT)) {
            "male" -> ((weightKg * ageFactor) + activityFactor).toString()
            "female" -> ((weightKg * ageFactor) + activityFactor).toString()
            else -> 0.0.toString()
        }
    }

}