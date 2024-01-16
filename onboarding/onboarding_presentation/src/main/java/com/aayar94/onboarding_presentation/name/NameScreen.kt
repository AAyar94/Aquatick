package com.aayar94.onboarding_presentation.name

import com.aayar94.core.R.string as AppText
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.aayar94.core_ui.theme.LocalSpacing
import com.aayar94.onboarding_presentation.component.AppTextField
import com.aayar94.onboarding_presentation.component.BasicAppTextField

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NameScreen(
    scaffoldState: ScaffoldState,
    onNextClick: () -> Unit,
    viewModel: NameViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val keyboardController = LocalSoftwareKeyboardController.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(id = AppText.enter_your_name))
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            BasicAppTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                value = viewModel.nameState,
                onValueChange = viewModel::nameChange,
                unit = null,
                textStyle = MaterialTheme.typography.h2,
                keyboardType = KeyboardType.Text
            )
            Spacer(modifier = Modifier.height(spacing.spaceLarge))
            Button(onClick = viewModel::onNextClick) {
                Text(text = stringResource(id = AppText.next))
            }
        }
    }
}