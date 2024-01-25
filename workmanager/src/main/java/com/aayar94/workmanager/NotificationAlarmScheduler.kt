package com.aayar94.workmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent

class NotificationAlarmScheduler(
    private val context: Context
) : AlarmScheduler {

    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    override fun onCreatePendingIntent(reminderModel: IntakeReminderModel): PendingIntent {
        val intent = Intent(context, AlarmReceiver::class.java)

        return PendingIntent.getBroadcast(
            context,
            reminderModel.id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    override fun schedule(reminderModel: IntakeReminderModel) {
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            reminderModel.time,
            AlarmManager.INTERVAL_DAY,
            onCreatePendingIntent(reminderModel)
        )
    }

    override fun cancel(reminderModel: IntakeReminderModel) {
        alarmManager.cancel(
            onCreatePendingIntent(reminderModel)
        )
    }
}