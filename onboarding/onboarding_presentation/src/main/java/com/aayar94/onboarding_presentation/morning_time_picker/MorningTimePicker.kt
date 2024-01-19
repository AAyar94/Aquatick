package com.aayar94.onboarding_presentation.morning_time_picker

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.aayar94.core.util.UiEvent
import com.aayar94.core_ui.theme.LocalShape
import com.aayar94.core_ui.theme.LocalSpacing
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MorningTimePicker(
    viewModel: MorningTimeViewModel = hiltViewModel(),
    onNextClick: () -> Unit
) {
    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNextClick()
                else -> Unit
            }
        }
    }
    val morningTimeSetter = rememberTimePickerState(7, 0, true)
    val spacing = LocalSpacing.current
    val shapes = LocalShape.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.spaceMedium),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = com.aayar94.core.R.string.enter_your_get_up_time),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyMedium
            )
            TimePicker(
                state = morningTimeSetter,
                modifier = Modifier.padding(horizontal = spacing.spaceExtraSmall),
                layoutType = TimePickerLayoutType.Vertical
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Text(
                text = stringResource(id = com.aayar94.core.R.string.this_clock_will_used_for),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            FilledTonalButton(
                onClick = {
                    viewModel.timeChangedUpdate(timeFormatter(morningTimeSetter))
                    viewModel.onNextClicked()
                },
                shape = shapes.mediumCornerRadius
            ) {
                Text(
                    text = stringResource(id = com.aayar94.core.R.string.next),
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun timeFormatter(morningTimeSetter:TimePickerState):String{
    val time: Int = (morningTimeSetter.minute* 60 + morningTimeSetter.hour* 60 * 60) * 1000
    val format = SimpleDateFormat("HH:mm:ss")
    return  format.format(time)
}