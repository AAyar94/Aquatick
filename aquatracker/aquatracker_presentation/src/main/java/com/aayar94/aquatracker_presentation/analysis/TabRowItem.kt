package com.aayar94.aquatracker_presentation.analysis

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Today
import androidx.compose.material.icons.filled.ViewWeek
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.aayar94.aquatracker_presentation.analysis.sub_screen.daily.DailyScreen
import com.aayar94.aquatracker_presentation.analysis.sub_screen.monthly.MonthlyScreen
import com.aayar94.aquatracker_presentation.analysis.sub_screen.weekly.WeeklyScreen

enum class TabRowItem(
    val text: String,
    val icon: ImageVector,
    val index: Int,
    val screen: @Composable () -> Unit
) {
    Daily(
        text = "Daily",
        icon = Icons.Default.Today,
        index = 0,
        screen = { DailyScreen() }
    ),
    Weekly(
        text = "Weekly",
        icon = Icons.Default.ViewWeek,
        index = 1,
        screen = { WeeklyScreen() }

    ),
    Monthly(
        text = "Monthly",
        icon = Icons.Default.CalendarMonth,
        index = 2,
        screen =
        { MonthlyScreen() }
    )
}

