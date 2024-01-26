package com.aayar94.settings_presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.NotificationsOff
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aayar94.core_ui.theme.LocalSpacing
import com.aayar94.core_ui.util.DevicesPreview
import com.aayar94.core_ui.theme.AquatickTheme
import com.aayar94.core.R.string as AppText

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val uiState = viewModel.uiState.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = spacing.spaceMedium,
                    vertical = spacing.spaceMedium
                ),
            verticalArrangement = Arrangement.spacedBy(spacing.spaceMedium),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = AppText.settings),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.primary
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = AppText.notification),
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.weight(1f))
                Switch(
                    checked = uiState.value.isNotificationEnabled,
                    onCheckedChange = viewModel::onNotificationSwitch,
                    thumbContent = {
                        if (uiState.value.isNotificationEnabled) Icon(
                            imageVector = Icons.Default.NotificationsActive,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                        else {
                            Icon(
                                imageVector = Icons.Default.NotificationsOff,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }

                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = AppText.dark_theme),
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.weight(1f))
                Switch(
                    checked = uiState.value.isDarkThemeEnabled,
                    onCheckedChange = viewModel::onDarkSwitch,
                    thumbContent = {
                        if (uiState.value.isDarkThemeEnabled) Icon(
                            imageVector = Icons.Default.NightsStay,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                        else Icon(
                            imageVector = Icons.Default.WbSunny,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                )
            }
        }
    }
}


@DevicesPreview
@Composable
fun PreviewSettingsScreen() {
    AquatickTheme {
        SettingsScreen()
    }
}