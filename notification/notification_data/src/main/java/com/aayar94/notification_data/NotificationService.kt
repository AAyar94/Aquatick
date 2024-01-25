package com.aayar94.notification_data

import android.annotation.SuppressLint
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class NotificationService : FirebaseMessagingService() {

    @SuppressLint("MissingPermission")
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Handle the incoming message and show a notification.
        val notificationTitle = remoteMessage.notification?.title
        val notificationBody = remoteMessage.notification?.body
        val notificationTime = remoteMessage.sentTime
        val notificationBuilder = NotificationCompat.Builder(this, "aquatick_post_notification")
            .setSmallIcon(com.aayar94.core.R.drawable.water_intake_card_image)
            .setContentTitle(notificationTitle)
            .setContentText(notificationBody)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(remoteMessage.messageId?.toInt() ?: 0, notificationBuilder.build())
    }
}