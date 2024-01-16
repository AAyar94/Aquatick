package com.aayar94.aquatick.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.aayar94.aquatick.core.navigation.AppNavigation
import com.aayar94.aquatick.core.navigation.NavigationManager
import com.aayar94.aquatick.core.navigation.Route
import com.aayar94.aquatick.ui.screen.onboarding.Onboarding
import com.aayar94.core.domain.preferences.Preferences
import com.example.compose.AquatickTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        val shouldShowOnboarding = preferences.loadOnboardingState()
        setContent {
            AquatickTheme {
                AppNavigation(if (shouldShowOnboarding) Route.WELCOME else Route.HOME)
            }
        }
    }
}
