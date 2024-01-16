package com.aayar94.onboarding_presentation.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.aayar94.core.R
import com.aayar94.core_ui.theme.LocalSpacing
import com.aayar94.core_ui.util.DevicesPreview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.aayar94.core.R.string as AppText

@Composable
fun WelcomeScreen(
    onNextClick: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(MaterialTheme.colors.background)
    systemUiController.setNavigationBarColor(MaterialTheme.colors.background)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val spacing = LocalSpacing.current
        Text(
            text = stringResource(id = AppText.hello_welcome),
            color = MaterialTheme.colors.onBackground
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        Text(
            text = stringResource(AppText.lets_continue),
            color = MaterialTheme.colors.onBackground
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        Button(onClick = onNextClick) {
            Text(text = stringResource(id = R.string.next))
        }
    }
}
