package com.aayar94.aquatick

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aayar94.aquatick.core.navigation.AppNavigation
import com.example.compose.AquatickTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AquatickTheme {
                AppNavigation()
            }
        }
    }
}
