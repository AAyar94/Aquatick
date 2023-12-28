package com.aayar94.aquatick.core.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aayar94.aquatick.ui.screen.drink.drinkGraph
import com.aayar94.aquatick.ui.screen.home.homeGraph
import com.aayar94.aquatick.ui.screen.settings.settingsGraph
import com.aayar94.aquatick.ui.screen.splash.splashGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val bottomBarItems = NavigationManager.bottomBarItems
    val startDestinationRoute = NavigationManager.startDestination.route


    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        currentRoute?.let { currentRoute ->
            if (NavigationManager.isBottomBarItem(currentRoute)) {
                AppNavigationBar(
                    navController = navController,
                    items = bottomBarItems,
                    onItemClick = {
                        navController.navigate(it.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(startDestinationRoute) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = startDestinationRoute,
            modifier = Modifier.padding(paddingValues)
        ) {
            splashGraph(navController)
            homeGraph(navController)
            drinkGraph(navController)
            settingsGraph(navController)
        }
    }
}