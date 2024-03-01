package com.aayar94.settings_presentation

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.NotificationsOff
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aayar94.core.util.UiEvent
import com.aayar94.core_ui.theme.AquatickTheme
import com.aayar94.core_ui.theme.LocalSpacing
import com.aayar94.core_ui.util.DevicesPreview
import com.aayar94.settings_presentation.component.ColorScheme
import com.example.compose.BlueColorScheme
import com.example.compose.GreenColorScheme
import com.example.compose.RedColorScheme
import com.example.compose.YellowColorScheme
import com.aayar94.core.R.string as AppText

@Composable
fun SettingsScreen(
    onDeleteApp: () -> Unit, viewModel: SettingsViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect {
            when (it) {
                is UiEvent.Success -> onDeleteApp()
                else -> Unit
            }
        }
    }
    val spacing = LocalSpacing.current
    val uiState = viewModel.uiState.collectAsState()
    var deleteAllAlarmVisibility by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.TopCenter
    ) {
        if (deleteAllAlarmVisibility) {
            AlertDialog(onDismissRequest = { deleteAllAlarmVisibility = false },
                title = { Text(stringResource(id = AppText.delete_all)) },
                text = { Text(stringResource(id = AppText.this_deletes_everything)) },
                confirmButton = {
                    FilledTonalButton(
                        onClick = { deleteAllAlarmVisibility = false },
                        colors = ButtonDefaults.filledTonalButtonColors(
                            contentColor = MaterialTheme.colorScheme.onErrorContainer,
                            containerColor = MaterialTheme.colorScheme.errorContainer
                        )
                    ) {
                        Text(stringResource(id = AppText.yes_im_sure))
                    }
                },
                dismissButton = {
                    Button(onClick = { deleteAllAlarmVisibility = false }) {
                        Text(stringResource(id = AppText.cancel))
                    }
                })
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = spacing.spaceMedium, vertical = spacing.spaceMedium
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
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = AppText.notification),
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.weight(1f))
            Switch(checked = uiState.value.isNotificationEnabled,
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
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = AppText.use_system_dark_mode),
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = uiState.value.isSystemThemeEnabled,
                onCheckedChange = viewModel::changeSystemThemeSettings
            )
        }
        AnimatedVisibility(visible = !uiState.value.isSystemThemeEnabled) {
            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (uiState.value.isDarkThemeEnabled){
                        stringResource(id = AppText.dark_theme)
                    }else{
                        stringResource(id = AppText.light_theme)},
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.weight(1f))
                Switch(checked = uiState.value.isDarkThemeEnabled,
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
                    })
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            FilledTonalButton(
                onClick = { viewModel.deleteEverythingAndCloseApp() },
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                )
            ) {
                Icon(imageVector = Icons.Default.DeleteForever, contentDescription = null)
                Text(text = stringResource(id = AppText.delete_all_data))
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            ColorScheme(
                color = BlueColorScheme,
                isSelected = uiState.value.colorSchemeModel == BlueColorScheme,
                onClick = viewModel::onColorSchemeChange
            )
            ColorScheme(
                color = GreenColorScheme,
                isSelected = uiState.value.colorSchemeModel == GreenColorScheme,
                onClick = viewModel::onColorSchemeChange
            )
            ColorScheme(
                color = RedColorScheme,
                isSelected = uiState.value.colorSchemeModel == RedColorScheme,
                onClick = viewModel::onColorSchemeChange
            )
            ColorScheme(
                color = YellowColorScheme,
                isSelected = uiState.value.colorSchemeModel == YellowColorScheme,
                onClick = viewModel::onColorSchemeChange
            )
        }
    }
}


@DevicesPreview
@Composable
fun PreviewSettingsScreen() {
    AquatickTheme {
        SettingsScreen({})
    }
}