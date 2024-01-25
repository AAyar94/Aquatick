package com.aayar94.notification_presentetion

import com.aayar94.notification_domain.model.NotificationItem

data class NotificationUIState(
    val list: List<NotificationItem> = emptyList()
)