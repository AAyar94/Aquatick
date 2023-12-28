package com.aayar94.aquatick.ui.screen.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aayar94.aquatick.core.navigation.IBottomBarItem
import com.aayar94.aquatick.util.DevicesPreview

fun NavGraphBuilder.homeGraph(navController: NavController){
    composable(Home.route){
        HomeScreen(navController)
    }
}

data object Home :IBottomBarItem{
    override val icon: @Composable () -> Unit
        get() = { Icon(imageVector = Icons.Default.Home, contentDescription = null) }
    override val label: String
        get() = "Home"
    override val route: String
        get() = "route_home"
}
@Composable
fun HomeScreen(navController: NavController){

}

@DevicesPreview
@Composable
fun PreviewHomeScreen(){
    HomeScreen(navController = rememberNavController())
}