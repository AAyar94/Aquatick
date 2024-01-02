package com.aayar94.aquatick.ui.screen.callculate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class CalculateViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState = MutableStateFlow(CalculateUIState())
    val uiState = _uiState.asStateFlow()

    fun calculateIntake() {
        viewModelScope.launch {
            stepOne()
            delay(1000)
            stepTwo()
            delay(1000)
            stepThree()
            delay(1000)
            stepFour()
            delay(1000)
            stepFive()
            delay(2000)
            calculate()
        }

    }

    private fun stepOne() {
        _uiState.update {
            it.copy(stepOneVisibility = true)
        }
    }

    private fun stepTwo() {
        _uiState.update {
            it.copy(stepTwoVisibility = true)
        }
    }

    private fun stepThree() {
        _uiState.update {
            it.copy(stepThreeVisibility = true)
        }
    }

    private fun stepFour() {
        _uiState.update {
            it.copy(stepFourVisibility = true)
        }
    }

    private fun stepFive() {
        _uiState.update {
            it.copy(stepFiveVisibility = true)
        }
    }

    private fun calculate() {
        _uiState.update {
            it.copy(calculatedIntakeAmount = "1500 ml")
        }
    }
}