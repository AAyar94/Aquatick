package com.aayar94.aquatick.navigation

import android.os.Build
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aayar94.aquatracker_presentation.analysis.AnalysisScreen
import com.aayar94.aquatracker_presentation.article.ArticleScreen
import com.aayar94.aquatracker_presentation.drink.DrinkScreen
import com.aayar94.aquatracker_presentation.home.HomeScreen
import com.aayar94.notification_presentetion.NotificationScreen
import com.aayar94.onboarding_presentation.activity_level.ActivityLevelScreen
import com.aayar94.onboarding_presentation.age.AgeScreen
import com.aayar94.onboarding_presentation.daily_intake_calculation.DailyIntakeCalculation
import com.aayar94.onboarding_presentation.gender.GenderScreen
import com.aayar94.onboarding_presentation.morning_time_picker.MorningTimePicker
import com.aayar94.onboarding_presentation.name.NameScreen
import com.aayar94.onboarding_presentation.night_time_picker.NightTimePicker
import com.aayar94.onboarding_presentation.notification_permission.NotificationPermission
import com.aayar94.onboarding_presentation.weight.WeightScreen
import com.aayar94.onboarding_presentation.welcome.WelcomeScreen
import com.aayar94.settings_presentation.SettingsScreen

@Composable
fun AppNavigation(
    startDestinationRoute: String,
    onDeleteApp: () -> Unit
) {
    val navController = rememberNavController()
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    Scaffold(modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        bottomBar = {
            val currentRoute = navController.currentBackStackEntryAsState().value
            bottomBarItems.forEach { bottomBarItem ->
                if (currentRoute != null) {
                    if (currentRoute.destination.route == bottomBarItem.route) {
                        BottomNavigationBar(
                            navController = navController,
                            onItemClick = { route ->
                                navController.navigate(route)
                            }
                        )
                    }
                }
            }
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
                NameScreen(snackBarHostState = snackBarHostState,
                    onNextClick = { navController.navigate(Route.AGE) })
            }
            composable(Route.AGE) {
                AgeScreen(snackBarHostState = snackBarHostState,
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
                NightTimePicker(onNextClick = {
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                        navController.navigate(Route.DAILY_INTAKE_CALCULATION)
                    } else {
                        navController.navigate(Route.NOTIFICATION_PERMISSION)
                    }
                })
            }
            composable(Route.NOTIFICATION_PERMISSION) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    NotificationPermission(onNextClicked = { navController.navigate(Route.DAILY_INTAKE_CALCULATION) })
                }
            }
            composable(Route.DAILY_INTAKE_CALCULATION) {
                DailyIntakeCalculation(finishOnboardingClicked = { navController.navigate(Route.HOME) })
            }
            composable(Route.HOME) {
                HomeScreen(onDrinkNavigateClick = { navController.navigate(Route.DRINK) },
                    onArticleClick = { article ->
                        navController.navigate(Route.ARTICLE + "/$article")
                    },
                    onNotificationIconClick = { navController.navigate(Route.NOTIFICATION) },
                    onAnalysisButtonClick = { navController.navigate(Route.ANALYSIS) })
            }
            composable(Route.ARTICLE + "/{id}", arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })) {
                val id = it.arguments?.getInt("id")!!
                ArticleScreen(articleId = id)
            }
            composable(Route.NOTIFICATION) {
                NotificationScreen()
            }
            composable(Route.DRINK) {
                DrinkScreen(onNavigate = {})
            }
            composable(Route.SETTINGS) {
                SettingsScreen(onDeleteApp = onDeleteApp)
            }
            composable(Route.ANALYSIS) {
                AnalysisScreen()
            }
        }
    }
}