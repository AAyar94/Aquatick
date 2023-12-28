package com.aayar94.aquatick.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.aayar94.aquatick.core.navigation.AppNavigation
import com.aayar94.aquatick.core.navigation.NavigationManager
import com.aayar94.aquatick.ui.screen.onboarding.Onboarding
import com.example.compose.AquatickTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            if (viewModel.readOnboardingState()){
                NavigationManager.startDestination=Onboarding
            }
        }
        setContent {
            AquatickTheme {
                AppNavigation()
            }
        }
    }
}
