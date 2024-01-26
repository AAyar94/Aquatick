package com.aayar94.onboarding_presentation.notification_permission

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aayar94.core.util.UiEvent
import com.aayar94.core_ui.R
import com.aayar94.core_ui.theme.LocalShape
import com.aayar94.core_ui.theme.LocalSpacing
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun NotificationPermission(
    onNextClicked: () -> Unit,
    viewModel: NotificationPermissionViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect {
            when (it) {
                is UiEvent.Success -> onNextClicked()
                else -> Unit
            }
        }
    }
    val spacing = LocalSpacing.current
    val shapes = LocalShape.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.notification))
        LottieAnimation(
            composition = composition,
            modifier = Modifier.defaultMinSize(64.dp, 64.dp),
            iterations = 5
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        Text(
            text = stringResource(
                com.aayar94.core.R.string.grand_notification_permission,
            ),
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        FilledTonalButton(onClick = viewModel::onNextClicked, shape = shapes.mediumCornerRadius) {
            Text(text = stringResource(id = com.aayar94.core.R.string.next))
        }
    }
}