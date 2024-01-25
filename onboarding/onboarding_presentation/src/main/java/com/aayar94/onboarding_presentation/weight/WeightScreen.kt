package com.aayar94.onboarding_presentation.weight

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.aayar94.core.util.UiEvent
import com.aayar94.core_ui.theme.LocalShape
import com.aayar94.core_ui.theme.LocalSpacing
import com.aayar94.onboarding_presentation.component.BasicAppTextField

@Composable
fun WeightScreen(
    onNextClick: () -> Unit,
    viewModel: WeightViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNextClick()
                else -> Unit
            }
        }
    }
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = com.aayar94.core.R.string.enter_your_weight),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            BasicAppTextField(
                value = viewModel.weightState.toString(),
                onValueChange = {
                    viewModel.weightChange(
                        it.toFloat()
                    )
                },
                keyboardType = KeyboardType.Number
            )
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