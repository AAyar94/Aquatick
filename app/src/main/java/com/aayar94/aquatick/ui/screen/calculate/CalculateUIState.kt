package com.aayar94.aquatick.ui.screen.calculate

import com.aayar94.aquatick.core.base.BaseUiState

data class CalculateUIState(
    override val isLoading: Boolean = true,
    override val error: String? = null,
    val stepOneVisibility: Boolean = false,
    val stepTwoVisibility: Boolean = false,
    val stepThreeVisibility: Boolean = false,
    val stepFourVisibility: Boolean = false,
    val stepFiveVisibility: Boolean = false,
    var calculatedIntakeAmount: String? = null,
) : BaseUiState(isLoading, error)