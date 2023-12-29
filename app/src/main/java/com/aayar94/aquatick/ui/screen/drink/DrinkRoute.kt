package com.aayar94.aquatick.ui.screen.drink

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aayar94.aquatick.core.navigation.IBottomBarItem
import com.aayar94.aquatick.ui.component.WorkInProgress
import com.aayar94.aquatick.util.DevicesPreview

fun NavGraphBuilder.drinkGraph(navController: NavController) {
    composable(Drink.route) {
        DrinkScreen(navController)
    }
}

data object Drink : IBottomBarItem {
    override val icon: @Composable () -> Unit
        get() = { Icon(imageVector = Icons.Default.LocalDrink, contentDescription = null) }
    override val label: String
        get() = "Drink"
    override val route: String
        get() = "route_drink"

}

@Composable
fun DrinkScreen(navController: NavController) {
    WorkInProgress(where = "Drink")
}


@DevicesPreview
@Composable
fun PreviewDrinkScreen() {
    DrinkScreen(navController = rememberNavController())
}