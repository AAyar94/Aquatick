package com.aayar94.onboarding_presentation.notification_permission

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.aayar94.core.util.UiEvent
import com.aayar94.core_ui.R
import com.aayar94.core_ui.theme.LocalShape
import com.aayar94.core_ui.theme.LocalSpacing
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NotificationPermission(
    onNextClicked: () -> Unit, viewModel: NotificationPermissionViewModel = hiltViewModel()
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
    val context = LocalContext.current
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
            verticalArrangement = Arrangement.spacedBy(spacing.spaceMedium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.notification_anim))
            LottieAnimation(
                composition = composition,
                modifier = Modifier
                    .defaultMinSize(64.dp, 64.dp)
                    .fillMaxWidth()
                    .height(200.dp),
                iterations = 5
            )
            Text(
                text = stringResource(
                    com.aayar94.core.R.string.we_need_permission
                ),
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
                maxLines = 2
            )
            OutlinedButton(
                onClick = {
                    requestPermission(context).apply {
                        viewModel.saveNotificationStatus(this)
                    }
                }, shape = shapes.mediumCornerRadius
            ) {
                Text(text = stringResource(id = com.aayar94.core.R.string.grand_permission))
            }
            FilledTonalButton(
                onClick = viewModel::onNextClicked, shape = shapes.mediumCornerRadius
            ) {
                Icon(imageVector = Icons.Filled.NavigateNext, contentDescription = null)
                Text(text = stringResource(id = com.aayar94.core.R.string.next))
            }

        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun requestPermission(context: Context): Boolean {
    val permissionState =
        ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
    if (permissionState == PackageManager.PERMISSION_DENIED) {
        ActivityCompat.requestPermissions(
            context as Activity, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1
        )
        return true
    } else {
        return false
    }
}


