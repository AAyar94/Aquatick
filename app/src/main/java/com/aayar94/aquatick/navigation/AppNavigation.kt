package com.aayar94.aquatick.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aayar94.onboarding_presentation.gender.GenderScreen
import com.aayar94.onboarding_presentation.name.NameScreen
import com.aayar94.onboarding_presentation.welcome.WelcomeScreen

@Composable
fun AppNavigation(
    startDestinationRoute: String
) {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = startDestinationRoute,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Route.WELCOME) {
                WelcomeScreen(onNextClick = { navController.navigate(Route.NAME) })
            }
            composable(Route.NAME) {
                NameScreen(
                    scaffoldState = scaffoldState,
                    onNextClick = { navController.navigate(Route.AGE) })
            }
            composable(Route.GENDER) {
                GenderScreen(onNextClick = { navController.navigate(Route.HOME) })
            }
            composable(Route.DRINK) {

            }
            composable(Route.SETTINGS) {

            }
            composable(Route.HOME) {
            }
        }
    }
}