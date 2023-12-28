package com.aayar94.aquatick.ui.screen.splash

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aayar94.aquatick.core.navigation.INavigationItem
import com.aayar94.aquatick.util.DevicesPreview

fun NavGraphBuilder.splashGraph(navController: NavController) {
    composable(Splash.route) {
        SplashScreen(navController)
    }
}

data object Splash : INavigationItem {
    override val route: String
        get() = "route_splash"
}

@Composable
fun SplashScreen(navController: NavController) {

}

@DevicesPreview
@Composable
fun PreviewSplashScreen() {
    SplashScreen(navController = rememberNavController())
}