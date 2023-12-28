package com.aayar94.aquatick.core.navigation

import com.aayar94.aquatick.ui.screen.drink.Drink
import com.aayar94.aquatick.ui.screen.home.Home
import com.aayar94.aquatick.ui.screen.settings.Settings

object NavigationManager {
    var startDestination: INavigationItem = Home
    var bottomBarItems: List<IBottomBarItem> = listOf(
        Drink,
        Home,
        Settings
    )

    fun isBottomBarItem(route: String): Boolean {
        return bottomBarItems.any { it.route == route }
    }
}