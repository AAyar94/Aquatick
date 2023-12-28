package com.aayar94.aquatick.core.navigation

import androidx.compose.runtime.Composable

interface IBottomBarItem : INavigationItem {
    val icon: @Composable () -> Unit
    val label: String
}