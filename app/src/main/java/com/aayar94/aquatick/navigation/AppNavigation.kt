package com.aayar94.aquatick.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aayar94.aquatracker_presentation.drink.DrinkScreen
import com.aayar94.aquatracker_presentation.home.HomeScreen
import com.aayar94.onboarding_presentation.activity_level.ActivityLevelScreen
import com.aayar94.onboarding_presentation.age.AgeScreen
import com.aayar94.onboarding_presentation.daily_intake_calculation.DailyIntakeCalculation
import com.aayar94.onboarding_presentation.gender.GenderScreen
import com.aayar94.onboarding_presentation.morning_time_picker.MorningTimePicker
import com.aayar94.onboarding_presentation.name.NameScreen
import com.aayar94.onboarding_presentation.night_time_picker.NightTimePicker
import com.aayar94.onboarding_presentation.weight.WeightScreen
import com.aayar94.onboarding_presentation.welcome.WelcomeScreen
import com.aayar94.settings_presentation.SettingsScreen

@Composable
fun AppNavigation(
    startDestinationRoute: String
) {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        bottomBar = {

        }
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
                    snackBarHostState = snackBarHostState,
                    onNextClick = { navController.navigate(Route.AGE) })
            }
            composable(Route.AGE) {
                AgeScreen(
                    snackBarHostState = snackBarHostState,
                    onNextClick = { navController.navigate(Route.GENDER) })
            }
            composable(Route.GENDER) {
                GenderScreen(onNextClick = { navController.navigate(Route.WEIGHT) })
            }
            composable(Route.WEIGHT) {
                WeightScreen(onNextClick = { navController.navigate(Route.ACTIVITY_LEVEL) })
            }
            composable(Route.ACTIVITY_LEVEL) {
                ActivityLevelScreen(onNextClick = { navController.navigate(Route.MORNING_TIME_PICKER) })
            }
            composable(Route.MORNING_TIME_PICKER) {
                MorningTimePicker(onNextClick = { navController.navigate(Route.NIGHT_TIME_PICKER) })
            }
            composable(Route.NIGHT_TIME_PICKER) {
                NightTimePicker(onNextClick = { navController.navigate(Route.DAILY_INTAKE_CALCULATION) })
            }
            composable(Route.DAILY_INTAKE_CALCULATION) {
                DailyIntakeCalculation(finishOnboardingClicked = { navController.navigate(Route.HOME) })
            }
            composable(Route.HOME) {
                HomeScreen(onDrinkNavigateClick = { navController.navigate(Route.DRINK) })
            }
            composable(Route.DRINK) {
                DrinkScreen(onNavigate = {})
            }
            composable(Route.SETTINGS) {
                SettingsScreen()
            }

        }
    }
}