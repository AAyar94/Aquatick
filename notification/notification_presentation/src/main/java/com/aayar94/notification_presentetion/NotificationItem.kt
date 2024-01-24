package com.aayar94.notification_presentetion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aayar94.core_ui.theme.LocalSpacing
import com.aayar94.core_ui.util.DevicesPreview
import com.example.compose.AquatickTheme
import java.time.LocalDateTime

@Composable
fun NotificationItem(
    imageLink: String? = null,
    notificationTitle: String,
    isReadBefore: Boolean,
    notificationTime: LocalDateTime
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .defaultMinSize(200.dp, 100.dp)
            .background(MaterialTheme.colorScheme.background)
            .padding(spacing.spaceMedium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        if (imageLink != null) {
            AsyncImage(model = imageLink, contentDescription = null)
        }
        Text(
            text = notificationTitle,
            fontWeight = if (isReadBefore) FontWeight.Normal else FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.weight(1f))
        Column {
            Text(text = "${notificationTime.hour} : ${notificationTime.minute}", color = MaterialTheme.colorScheme.onBackground)
            Text(text = "date", color = MaterialTheme.colorScheme.onBackground)
        }
    }
}


@DevicesPreview
@Composable
fun PreviewNotification() {
    AquatickTheme {
        Column {
            NotificationItem(
                imageLink = null,
                notificationTitle = "Notification 1",
                isReadBefore = false,
                notificationTime = LocalDateTime.now()
            )
            NotificationItem(
                imageLink = null,
                notificationTitle = "Notification 1",
                isReadBefore = false,
                notificationTime = LocalDateTime.now()
            )
            NotificationItem(
                imageLink = null,
                notificationTitle = "Notification 1",
                isReadBefore = false,
                notificationTime = LocalDateTime.now()
            )
        }
    }
}