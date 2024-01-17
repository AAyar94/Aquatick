package com.aayar94.aquatracker_presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.aayar94.core_ui.theme.LocalShape
import com.aayar94.core_ui.theme.LocalSpacing

@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    greetings: String,
    name: String,
    isNewNotification: Boolean
) {
    val spacing = LocalSpacing.current
    val shape = LocalShape.current
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = greetings,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                fontStyle = FontStyle.Italic
            )
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(shape.extraLargeCornerRadius)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .clickable {
                    //TODO Route Notification
                }
        ) {
            IconButton(
                onClick = { /*TODO Route Notification*/ },
                modifier = Modifier.padding(spacing.spaceSmall)
            ) {
                Icon(
                    imageVector = if (isNewNotification) Icons.Default.Notifications else Icons.Default.Notifications,
                    contentDescription = "Notification_Bell",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }

    }
}