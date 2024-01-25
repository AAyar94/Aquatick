package com.aayar94.notification_presentetion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.aayar94.core_ui.theme.LocalSpacing
import com.aayar94.core_ui.util.DevicesPreview
import com.aayar94.compose.AquatickTheme

@Composable
fun NotificationScreen(
    viewModel: NotificationViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val uiState = viewModel.uiState.collectAsState()
    val listState = rememberLazyListState()
    viewModel.onFetchNotifications()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(spacing.spaceLarge)
        ) {
            Text(
                text = stringResource(id = com.aayar94.core.R.string.notification),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                state = listState, verticalArrangement = Arrangement.spacedBy(spacing.spaceMedium),
                reverseLayout = true
            ) {
                items(uiState.value.list) {
                    NotificationItemView(
                        imageLink = it.image,
                        notificationTitle = it.title,
                        isReadBefore = true,
                        notificationTime = it.time
                    )
                }
            }
        }
    }
}


@DevicesPreview
@Composable
fun PreviewNotificationScreen() {
    AquatickTheme {
        NotificationScreen()
    }
}