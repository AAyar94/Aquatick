package com.aayar94.aquatracker_presentation.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import com.aayar94.core_ui.theme.LocalSpacing

@Composable
fun HomeHeader(
    greetings: String, name: String, isNewNotification: Boolean, modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Column {
            Text(
                text = greetings,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground,
                fontStyle = FontStyle.Italic
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Text(
                text = name,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = if (isNewNotification) Icons.Default.Notifications else Icons.Default.Notifications,
                contentDescription = "Notification_Bell"
            )
        }
    }
}