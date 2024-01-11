package com.aayar94.aquatick.ui.screen.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.NotificationsOff
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aayar94.aquatick.R
import com.aayar94.aquatick.core.navigation.IBottomBarItem
import com.aayar94.aquatick.ui.screen.setup.Setup
import com.aayar94.aquatick.util.DevicesPreview
import com.example.compose.AquatickTheme

fun NavGraphBuilder.settingsGraph(navController: NavController) {
    composable(Settings.route) {
        val viewModel: SettingsViewModel = hiltViewModel()
        SettingsScreen(navController, viewModel)
    }
}

data object Settings : IBottomBarItem {
    override val icon: @Composable () -> Unit
        get() = { Icon(imageVector = Icons.Default.Settings, contentDescription = null) }
    override val label: String
        get() = "Settings"
    override val route: String
        get() = "route_settings"
}

@Composable
fun SettingsScreen(navController: NavController, viewModel: SettingsViewModel) {
    val uiState = viewModel.uiState.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 12.dp, horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = R.string.settings),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Divider(
                modifier = Modifier.fillMaxWidth(0.95f),
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.secondary
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.notification),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Switch(
                    checked = uiState.value.isNotificationEnabled,
                    onCheckedChange = {
                        viewModel.updateNotificationStatus(it)
                    },
                    thumbContent = {
                        if (uiState.value.isNotificationEnabled) {
                            Icon(
                                imageVector = Icons.Default.NotificationsActive,
                                contentDescription = null
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.NotificationsOff,
                                contentDescription = null
                            )
                        }
                    })
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.remove_ads),
                    color = MaterialTheme.colorScheme.onBackground
                )
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.Magenta
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.unit),
                    color = MaterialTheme.colorScheme.onBackground
                )
                Column {
                    Text(
                        text = if (uiState.value.weightUnit) stringResource(R.string.kg) else stringResource(
                            R.string.lbs
                        ),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = if (uiState.value.liquidUnit) stringResource(R.string.ml) else stringResource(
                            R.string.oz
                        ),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 12.dp)
                    .clickable { navController.navigate(Setup.route) },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Setup Again", color = MaterialTheme.colorScheme.onBackground)
                Icon(
                    imageVector = Icons.Default.ArrowForwardIos,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

@DevicesPreview
@Composable
fun PreviewSettingsScreen() {
    AquatickTheme {
        SettingsScreen(navController = rememberNavController(), viewModel = hiltViewModel())
    }
}
