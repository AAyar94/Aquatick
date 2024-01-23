package com.aayar94.onboarding_presentation.activity_level

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.aayar94.core.domain.model.ActivityLevel
import com.aayar94.core.util.UiEvent
import com.aayar94.core_ui.theme.LocalShape
import com.aayar94.core_ui.theme.LocalSpacing
import com.aayar94.core.R.drawable as AppDrawable
import com.aayar94.core.R.string as AppText

@Composable
fun ActivityLevelScreen(
    viewModel: ActivityLevelViewModel = hiltViewModel(), onNextClick: () -> Unit
) {
    val spacing = LocalSpacing.current
    val shapes = LocalShape.current
    val scrollState = rememberScrollState()
    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNextClick()
                else -> Unit
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.spaceMedium).verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = com.aayar94.core.R.string.select_your_activity_level),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(spacing.spaceExtraLarge),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ActivityLevelRow(
                    activityLevel = ActivityLevel.VeryActive,
                    activityLevelName = stringResource(id = AppText.activity_level_very_active),
                    drawableImage = AppDrawable.activity_level_fit,
                    isSelected = viewModel.activityLevelState == ActivityLevel.VeryActive,
                    onSelect = { viewModel.activityLevelChange(ActivityLevel.VeryActive) }
                )
                ActivityLevelRow(
                    activityLevel = ActivityLevel.Active,
                    activityLevelName = stringResource(id = AppText.activity_level_active),
                    drawableImage = AppDrawable.activity_level_active,
                    isSelected = viewModel.activityLevelState == ActivityLevel.Active,
                    onSelect = { viewModel.activityLevelChange(ActivityLevel.Active) }
                )
                ActivityLevelRow(
                    activityLevel = ActivityLevel.Average,
                    activityLevelName = stringResource(id = AppText.activity_level_average),
                    drawableImage = AppDrawable.activity_level_very_active,
                    isSelected = viewModel.activityLevelState == ActivityLevel.Average,
                    onSelect = { viewModel.activityLevelChange(ActivityLevel.Average) }
                )
                ActivityLevelRow(
                    activityLevel = ActivityLevel.LessThanAverage,
                    activityLevelName = stringResource(id = AppText.activity_level_less_than_average),
                    drawableImage = AppDrawable.activity_level_less_than_average,
                    isSelected = viewModel.activityLevelState == ActivityLevel.LessThanAverage,
                    onSelect = { viewModel.activityLevelChange(ActivityLevel.LessThanAverage) }
                )
                ActivityLevelRow(
                    activityLevel = ActivityLevel.NotActive,
                    activityLevelName = stringResource(id = AppText.activity_level_not_active),
                    drawableImage = AppDrawable.activity_level_not_active2,
                    isSelected = viewModel.activityLevelState == ActivityLevel.NotActive,
                    onSelect = { viewModel.activityLevelChange(ActivityLevel.NotActive) }
                )
            }
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            FilledTonalButton(onClick = viewModel::onNextClick, shape = shapes.mediumCornerRadius) {
                Text(
                    text = stringResource(id = com.aayar94.core.R.string.next),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}