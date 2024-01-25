package com.aayar94.notification_presentetion

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aayar94.core_ui.theme.LocalShape
import com.aayar94.core_ui.theme.LocalSpacing
import com.aayar94.core_ui.util.DevicesPreview
import com.aayar94.core_ui.theme.AquatickTheme
import java.time.LocalDateTime

@Composable
fun NotificationItemView(
    imageLink: String? = null,
    notificationTitle: String,
    isReadBefore: Boolean,
    notificationTime: LocalDateTime
) {
    val spacing = LocalSpacing.current
    val shapes = LocalShape.current
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .defaultMinSize(200.dp, 100.dp)
            .background(MaterialTheme.colorScheme.background),
        shape = shapes.extraLargeCornerRadius,
        colors = CardDefaults.outlinedCardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.onBackground)
    ) {
        Row(
            modifier = Modifier.padding(spacing.spaceMedium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            if (imageLink != null) {
                AsyncImage(
                    model = imageLink,
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .padding(spacing.spaceSmall),
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                text = notificationTitle,
                fontWeight = if (isReadBefore) FontWeight.Normal else FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${notificationTime.hour} : ${notificationTime.minute}",
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "${notificationTime.dayOfMonth} / ${notificationTime.monthValue} / ${notificationTime.year}",
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@DevicesPreview
@Composable
fun PreviewNotification() {
    AquatickTheme {
        Column {
            NotificationItemView(
                imageLink = "https://contenthub-static.grammarly.com/blog/wp-content/uploads/2023/12/5376-Dec_blog-header_New-Year-Message_A_V1.png",
                notificationTitle = "Notification 1",
                isReadBefore = false,
                notificationTime = LocalDateTime.now()
            )
            NotificationItemView(
                imageLink = null,
                notificationTitle = "Notification 1",
                isReadBefore = false,
                notificationTime = LocalDateTime.now()
            )
            NotificationItemView(
                imageLink = null,
                notificationTitle = "Notification 1",
                isReadBefore = false,
                notificationTime = LocalDateTime.now()
            )
        }
    }
}