package com.aayar94.onboarding_presentation.age

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aayar94.core.util.UiEvent
import com.aayar94.core_ui.theme.LocalShape
import com.aayar94.core_ui.theme.LocalSpacing
import com.aayar94.onboarding_presentation.component.BasicAppTextField
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.aayar94.core.R.string as AppText

@Composable
fun AgeScreen(
    snackBarHostState: SnackbarHostState,
    onNextClick: () -> Unit,
    viewModel: AgeViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val shapes = LocalShape.current
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNextClick()
                is UiEvent.ShowSnackbar -> {
                    snackBarHostState.showSnackbar(
                        message = event.message.asString(context = context)
                    )
                }

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
                .padding(spacing.spaceMedium),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(com.aayar94.core_ui.R.raw.age_anim))
            LottieAnimation(
                composition = composition, modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Text(
                text = stringResource(id = AppText.enter_your_age),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            BasicAppTextField(
                Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                value = viewModel.ageState,
                onValueChange = { viewModel.ageChange(it) },
                keyboardType = KeyboardType.Number,
                unit = "Years old",
                placeholder = "Your Age",
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
                    text = stringResource(id = AppText.next),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }
}