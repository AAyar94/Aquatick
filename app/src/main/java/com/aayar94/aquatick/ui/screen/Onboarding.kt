package com.aayar94.aquatick.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aayar94.aquatick.core.navigation.INavigationItem
import com.aayar94.aquatick.ui.component.WorkInProgress
import com.aayar94.aquatick.util.DevicesPreview
import com.example.compose.AquatickTheme

fun NavGraphBuilder.onboardingGraph(navController: NavController) {
    composable(Onboarding.route) {
        OnboardingScreen(navController)
    }
}

data object Onboarding : INavigationItem {
    override val route: String
        get() = "route_onboarding"
}

@Composable
fun OnboardingScreen(navController: NavController) {
    WorkInProgress(where = "Onboarding")
}

@DevicesPreview
@Composable
fun PreviewOnboardingScreen() {
    AquatickTheme {
        OnboardingScreen(navController = rememberNavController())
    }
}