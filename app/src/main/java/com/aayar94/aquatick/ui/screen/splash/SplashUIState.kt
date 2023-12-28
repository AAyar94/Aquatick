package com.aayar94.aquatick.ui.screen.splash

import com.aayar94.aquatick.core.base.BaseUiState

data class SplashUIState(
    override val isLoading: Boolean = true,
    override val error: String? = null,
    val isOnboardingCompleted: Boolean? = null,
    val route: String? = null
) : BaseUiState(isLoading, error)