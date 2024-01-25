package com.aayar94.notification_domain.model

import java.time.LocalDateTime

data class NotificationItem(
    val image:String?=null,
    val title:String,
    val time:LocalDateTime,
)