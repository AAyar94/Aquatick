package com.aayar94.workmanager

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.aayar94.core.util.UiText

class RunnerNotifier(
    notificationManager: NotificationManager,
    private val context: Context
) : Notifier(notificationManager) {

    override val notificationChannelId: String = "runner_channel_id"
    override val notificationChannelName: String = "Daily Reminder Notification"
    override val notificationId: Int = 200

    override fun buildNotification(): Notification {
        return NotificationCompat.Builder(context, notificationChannelId)
            .setContentTitle(getNotificationTitle())
            .setContentText(getNotificationMessage())
            .setSmallIcon(com.aayar94.core.R.drawable.water_intake_card_image)
            .build()
    }

    override fun getNotificationTitle(): String {
        return UiText.StringResource(com.aayar94.core.R.string.app_name).asString(context)
    }

    override fun getNotificationMessage(): String {
        return UiText.StringResource(com.aayar94.core.R.string.time_to_hydrate_yourself).asString(context)
    }
}