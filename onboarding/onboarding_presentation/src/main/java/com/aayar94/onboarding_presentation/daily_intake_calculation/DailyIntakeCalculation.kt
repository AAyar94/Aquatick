package com.aayar94.onboarding_presentation.daily_intake_calculation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.aayar94.core.util.UiEvent
import com.aayar94.core_ui.theme.LocalShape
import com.aayar94.core_ui.theme.LocalSpacing
import com.aayar94.core.R.string as AppText

@Composable
fun DailyIntakeCalculation(
    viewModel: DailyIntakeCalculationViewModel = hiltViewModel(),
    finishOnboardingClicked: () -> Unit
) {
    val spacing = LocalSpacing.current
    val shapes = LocalShape.current
    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> finishOnboardingClicked()
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
            Modifier
                .fillMaxWidth()
                .padding(spacing.spaceMedium),
            verticalArrangement = Arrangement.spacedBy(spacing.spaceMedium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(visible = viewModel.itemVisibilityState.firstItemVisibility == true) {
                CalculationAnimationItem(string = stringResource(id = AppText.name))
            }
            AnimatedVisibility(visible = viewModel.itemVisibilityState.secondItemVisibility == true) {
                CalculationAnimationItem(string = stringResource(id = AppText.age))
            }
            AnimatedVisibility(visible = viewModel.itemVisibilityState.thirdItemVisibility == true) {
                CalculationAnimationItem(string = stringResource(id = AppText.weight))
            }
            AnimatedVisibility(visible = viewModel.itemVisibilityState.fourthItemVisibility == true) {
                CalculationAnimationItem(string = stringResource(id = AppText.gender))
            }
            AnimatedVisibility(visible = viewModel.itemVisibilityState.fifthItemVisibility == true) {
                CalculationAnimationItem(string = stringResource(id = AppText.activity_level))
            }
            AnimatedVisibility(visible = viewModel.itemVisibilityState.sixthItemVisibility) {
                CalculationAnimationItem(string = stringResource(id = AppText.get_up_time))
            }
            AnimatedVisibility(visible = viewModel.itemVisibilityState.seventhItemVisibility) {
                CalculationAnimationItem(string = stringResource(id = AppText.going_bed_time))
            }
            AnimatedVisibility(visible = viewModel.itemVisibilityState.seventhItemVisibility == true) {
                viewModel.itemVisibilityState.dailyIntakeAmount?.let {
                    Text(
                        text = stringResource(id = AppText.Your_daily_intake_amount_is, it),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            AnimatedVisibility(visible = viewModel.itemVisibilityState.seventhItemVisibility == true) {
                FilledTonalButton(
                    onClick = viewModel::onFinishedClick,
                    shape = shapes.mediumCornerRadius
                ) {
                    Text(text = stringResource(id = AppText.finish_setup))
                }
            }
        }
    }
    viewModel.startAnim()
}