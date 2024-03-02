package com.aayar94.onboarding_presentation.weight

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aayar94.core.util.UiEvent
import com.aayar94.core_ui.R
import com.aayar94.core_ui.theme.LocalShape
import com.aayar94.core_ui.theme.LocalSpacing
import com.aayar94.onboarding_presentation.component.BasicAppTextField
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

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
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.weight_anim))
            LottieAnimation(
                composition = composition, modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Text(
                text = stringResource(id = com.aayar94.core.R.string.enter_your_weight),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            BasicAppTextField(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                value = viewModel.weightState,
                onValueChange = {
                    viewModel.weightChange(
                        it.toFloat()
                    )
                },
                unit = "kg",
                keyboardType = KeyboardType.Number,
                placeholder = "Your Weight",
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 48.sp,
                    textAlign = TextAlign.End
                ),
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            FilledTonalButton(onClick = viewModel::onNextClick, shape = shapes.mediumCornerRadius) {
                Icon(imageVector = Icons.Filled.NavigateNext, contentDescription = null)
                Text(
                    text = stringResource(id = com.aayar94.core.R.string.next),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}