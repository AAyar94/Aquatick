package com.aayar94.settings_presentation

data class SettingsUIState(
    val isNotificationEnabled: Boolean = false,
    val isDarkThemeEnabled: Boolean = false,
    val isProUser: Boolean = false
)