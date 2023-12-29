package com.aayar94.aquatick.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aayar94.aquatick.core.navigation.IBottomBarItem
import com.aayar94.aquatick.ui.component.DailyIntakeStatusCard
import com.aayar94.aquatick.ui.screen.drink.Drink
import com.aayar94.aquatick.util.DevicesPreview

fun NavGraphBuilder.homeGraph(navController: NavController) {
    composable(Home.route) {
        val viewModel: HomeViewModel = hiltViewModel()
        HomeScreen(navController, viewModel)
    }
}

data object Home : IBottomBarItem {
    override val icon: @Composable () -> Unit
        get() = { Icon(imageVector = Icons.Default.Home, contentDescription = null) }
    override val label: String
        get() = "Home"
    override val route: String
        get() = "route_home"
}

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel) {
    val uiState = viewModel.uiState.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = uiState.value.greetings.toString(),
                        style = MaterialTheme.typography.bodyMedium.copy(fontStyle = FontStyle.Italic),
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                    )
                    Text(
                        text = uiState.value.name.toString(),
                        style = MaterialTheme.typography.bodyMedium.copy(fontStyle = FontStyle.Italic),
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.defaultMinSize(32.dp, 32.dp),
                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Notifications,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
            DailyIntakeStatusCard(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .wrapContentHeight(),
                lastIntakeTime = if (uiState.value.lastIntakeTime.isNullOrBlank()) "You didn't drink today" else uiState.value.lastIntakeTime!!,
                lastIntakeAmount = if (uiState.value.lastIntakeAmount.isNullOrBlank()) "" else uiState.value.lastIntakeAmount!!,
                buttonOnclick = { navController.navigate(Drink.route) }
            )
        }
    }
}

@DevicesPreview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(navController = rememberNavController(), viewModel = hiltViewModel())
}