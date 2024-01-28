package com.aayar94.workmanager

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat

class RunnerNotifier(
    notificationManager: NotificationManager,
    private val context: Context
) : Notifier(notificationManager) {

    override val notificationChannelId: String = "runner_channel_id"
    override val notificationChannelName: String = "Running Notification"
    override val notificationId: Int = 200

    override fun buildNotification(): Notification {
        return NotificationCompat.Builder(context, notificationChannelId)
            .setContentTitle(getNotificationTitle())
            .setContentText(getNotificationMessage())
            .setSmallIcon(com.aayar94.core.R.drawable.water_intake_card_image)
            .build()
    }

    override fun getNotificationTitle(): String {
        return "‍️Aquatick"
    }

    override fun getNotificationMessage(): String {
        return "Time for hydrate yourself 💧"
    }
}