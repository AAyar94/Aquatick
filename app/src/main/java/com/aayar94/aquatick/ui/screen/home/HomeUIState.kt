package com.aayar94.aquatick.ui.screen.home

import com.aayar94.aquatick.core.base.BaseUiState

data class HomeUIState(
    override val isLoading: Boolean = true,
    override val error: String? = null,
    val name: String? = null,
    val greetings: String? = null,
    val lastIntakeTime: String? = null,
    val lastIntakeAmount: String? = null
) : BaseUiState(isLoading, error)