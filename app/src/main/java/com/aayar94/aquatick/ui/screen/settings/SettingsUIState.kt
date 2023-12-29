package com.aayar94.aquatick.ui.screen.settings

import com.aayar94.aquatick.core.base.BaseUiState

data class SettingsUIState(
    override val isLoading: Boolean = true,
    override val error: String? = null,
    val isDarkTheme: Boolean = false,
    val isNotificationEnabled: Boolean = false,
    val weightUnit: Boolean = true,
    val liquidUnit: Boolean = true
) : BaseUiState(isLoading, error)