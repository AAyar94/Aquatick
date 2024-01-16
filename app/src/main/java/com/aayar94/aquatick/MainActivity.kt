package com.aayar94.aquatick

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.aayar94.aquatick.navigation.AppNavigation
import com.aayar94.aquatick.navigation.Route
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
