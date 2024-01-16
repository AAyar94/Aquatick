package com.aayar94.onboarding_presentation.age

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.aayar94.core.util.UiEvent
import com.aayar94.core_ui.theme.LocalSpacing
import com.aayar94.onboarding_presentation.component.BasicAppTextField
import com.aayar94.core.R.string as AppText

@Composable
fun AgeScreen(
    scaffoldState: ScaffoldState,
    onNextClick: () -> Unit,
    viewModel: AgeViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNextClick()
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(
                            context = context
                        )
                    )
                }

                else -> Unit
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(id = AppText.enter_your_age))
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            BasicAppTextField(
                value = viewModel.ageState,
                onValueChange = { viewModel.ageChange(it) },
                keyboardType = KeyboardType.Number
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Button(onClick = viewModel::onNextClick, shape = MaterialTheme.shapes.medium) {
                Text(text = stringResource(id = AppText.next))
            }
        }
    }
}