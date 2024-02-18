package com.aayar94.aquatick.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.aayar94.aquatick.navigation.BottomBarItem.Companion.bottomBarItems

data class BottomBarItem(
    val route: String, val icon: Int, val label: Int
) {
    companion object {
        val bottomBarItems = listOf(
            BottomBarItem(
                Route.DRINK,
                com.aayar94.core.R.drawable.drink_icon,
                label = com.aayar94.aquatick.R.string.nav_bar_item_drink
            ),
            BottomBarItem(
                Route.HOME,
                com.aayar94.core.R.drawable.home_icon,
                label = com.aayar94.aquatick.R.string.nav_bar_item_home
            ),
            BottomBarItem(
                Route.SETTINGS,
                com.aayar94.core.R.drawable.settings_icon,
                label = com.aayar94.aquatick.R.string.nav_bar_item_settings
            ),
        )
    }
}

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    items: List<BottomBarItem> = bottomBarItems,
    onItemClick: (String) -> Unit
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    NavigationBar(
        modifier = modifier, containerColor = MaterialTheme.colorScheme.surface
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(painter = painterResource(id = item.icon), contentDescription = null)
                },
                label = {
                    Text(
                        text = stringResource(id = item.label),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                selected = item.route == currentRoute,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface
                ),
                onClick = { onItemClick(item.route) },
            )
        }
    }
}
