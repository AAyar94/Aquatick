package com.aayar94.onboarding_presentation.daily_intake_calculation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aayar94.core.util.UiEvent
import com.aayar94.core_ui.R
import com.aayar94.core_ui.theme.LocalShape
import com.aayar94.core_ui.theme.LocalSpacing
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.aayar94.core.R.string as AppText


@Composable
fun DailyIntakeCalculation(
    viewModel: DailyIntakeCalculationViewModel = hiltViewModel(),
    finishOnboardingClicked: () -> Unit
) {
    val spacing = LocalSpacing.current
    val shapes = LocalShape.current
    val uiState = viewModel.uiState.collectAsState()
    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> finishOnboardingClicked()
                else -> Unit
            }
        }
    }
    LaunchedEffect(key1 = true) { // Use LaunchedEffect with a key to ensure it runs only once
        viewModel.startAnim()
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
            horizontalAlignment = Alignment.Start
        ) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.set_user_data))
            LottieAnimation(
                composition = composition,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                iterations = 5,
            )
            AnimatedVisibility(visible = uiState.value.firstItemVisibility == true) {
                CalculationAnimationItem(string = stringResource(id = AppText.name))
            }
            AnimatedVisibility(visible = uiState.value.secondItemVisibility == true) {
                CalculationAnimationItem(string = stringResource(id = AppText.age))
            }
            AnimatedVisibility(visible = uiState.value.thirdItemVisibility == true) {
                CalculationAnimationItem(string = stringResource(id = AppText.weight))
            }
            AnimatedVisibility(visible = uiState.value.fourthItemVisibility == true) {
                CalculationAnimationItem(string = stringResource(id = AppText.gender))
            }
            AnimatedVisibility(visible = uiState.value.fifthItemVisibility == true) {
                CalculationAnimationItem(string = stringResource(id = AppText.activity_level))
            }
            AnimatedVisibility(visible = uiState.value.sixthItemVisibility) {
                CalculationAnimationItem(string = stringResource(id = AppText.get_up_time))
            }
            AnimatedVisibility(visible = uiState.value.seventhItemVisibility) {
                CalculationAnimationItem(string = stringResource(id = AppText.going_bed_time))
            }
            AnimatedVisibility(
                visible = uiState.value.seventhItemVisibility, modifier = Modifier.align(
                    Alignment.CenterHorizontally
                )
            ) {
                uiState.value.dailyIntakeAmount?.let {
                    Text(
                        text = stringResource(id = AppText.Your_daily_intake_amount_is, it),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            AnimatedVisibility(
                visible = uiState.value.seventhItemVisibility, modifier = Modifier.align(
                    Alignment.CenterHorizontally
                )
            ) {
                FilledTonalButton(
                    onClick = viewModel::onFinishedClick,
                    shape = shapes.mediumCornerRadius,
                    modifier = Modifier.align(
                        Alignment.CenterHorizontally
                    )
                ) {
                    Icon(imageVector = Icons.Filled.Check, contentDescription = null)
                    Text(text = stringResource(id = AppText.finish_setup))
                }
            }
        }
    }
}