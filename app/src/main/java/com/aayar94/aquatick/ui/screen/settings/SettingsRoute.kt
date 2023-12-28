package com.aayar94.aquatick.ui.screen.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aayar94.aquatick.core.navigation.IBottomBarItem
import com.aayar94.aquatick.util.DevicesPreview

fun NavGraphBuilder.settingsGraph(navController: NavController){
    composable(Settings.route){
        SettingsScreen(navController)
    }
}

data object Settings : IBottomBarItem{
    override val icon: @Composable () -> Unit
        get() = { Icon(imageVector = Icons.Default.Settings, contentDescription = null) }
    override val label: String
        get() = "Settings"
    override val route: String
        get() = "route_settings"
}

@Composable
fun SettingsScreen(navController: NavController){
    Box(modifier = Modifier.fillMaxSize()){
        Button(onClick = { navController.navigate("route_setup")}) {
            Text(text = "Setup")
        }
    }
}

@DevicesPreview
@Composable
fun PreviewSettingsScreen(){
    SettingsScreen(navController = rememberNavController())
}
