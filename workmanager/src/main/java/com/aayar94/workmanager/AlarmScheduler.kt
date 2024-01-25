package com.aayar94.workmanager

import android.app.PendingIntent

interface AlarmScheduler {

    fun onCreatePendingIntent(reminderModel: IntakeReminderModel):PendingIntent
    fun schedule(reminderModel: IntakeReminderModel)
    fun cancel(reminderModel: IntakeReminderModel)
}