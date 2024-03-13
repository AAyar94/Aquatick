package com.aayar94.settings_presentation

data class SettingsUIState(
    val isNotificationEnabled: Boolean = false,
    val isDarkThemeEnabled: Boolean = false,
    val isSystemThemeEnabled: Boolean = true,
    val isProUser: Boolean = false,
    val colorSchemeModel: String = "blue"
)