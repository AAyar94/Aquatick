package com.aayar94.settings_presentation

import androidx.compose.ui.graphics.Color
import com.example.compose.BlueColorScheme

data class SettingsUIState(
    val isNotificationEnabled: Boolean = false,
    val isDarkThemeEnabled: Boolean = false,
    val isSystemThemeEnabled: Boolean = false,
    val isProUser: Boolean = false,
    val colorSchemeModel: Color = BlueColorScheme
)