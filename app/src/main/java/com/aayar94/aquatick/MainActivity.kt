package com.aayar94.aquatick

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.aayar94.aquatick.navigation.AppNavigation
import com.aayar94.aquatick.navigation.Route
import com.aayar94.core.domain.preferences.Preferences
import com.aayar94.core.domain.usercase.CalculateReminderTimes
import com.aayar94.core_ui.theme.AquatickTheme
import com.aayar94.workmanager.IntakeReminderModel
import com.aayar94.workmanager.NotificationAlarmScheduler
import com.google.firebase.Firebase
import com.google.firebase.messaging.messaging
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var preferences: Preferences
    private val notificationAlarmScheduler by lazy {
        NotificationAlarmScheduler(context = applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        val shouldShowOnboarding = preferences.loadOnboardingState()
        setContent {
            AquatickTheme {
                AppNavigation(startDestinationRoute = if (shouldShowOnboarding) Route.WELCOME else Route.HOME,
                    onDeleteApp = { finishActivity(500) })
            }
        }

        val localPreferences: SharedPreferences = getSharedPreferences("shared_pref", MODE_PRIVATE)

        val morningHour = localPreferences.getInt(Preferences.KEY_GET_UP_TIME_HOUR, 0)
        val morningMinute = localPreferences.getInt(Preferences.KEY_GET_UP_TIME_MIN, 0)
        val bedTimeHour = localPreferences.getInt(Preferences.KEY_GOING_BED_TIME_HOUR, 0)
        val bedTimeMinute = localPreferences.getInt(Preferences.KEY_GOING_BED_MIN, 0)

        Firebase.messaging.subscribeToTopic("aquatick").addOnCompleteListener { task ->
            var msg = "Subscribed"
            if (!task.isSuccessful) {
                msg = "Subscribe Failed"
            }
            Log.d("NotificationSub", msg)
        }

        val notificationTimeList = CalculateReminderTimes().invoke(
            getUpHour = morningHour,
            getUpMin = morningMinute,
            bedTimeHour = bedTimeHour,
            bedTimeMin = bedTimeMinute,
            120
        )
        if (!preferences.readIsNotificationsSetBefore()) {
            var notificationId = 1
            notificationTimeList.forEach { localTime ->
                val reminderItem = IntakeReminderModel(
                    time = Calendar.getInstance().apply {
                        set(Calendar.HOUR_OF_DAY, localTime.hour)
                        set(Calendar.MINUTE, localTime.minute)
                    }.timeInMillis,
                    id = notificationId,
                )
                notificationId += 1
                notificationAlarmScheduler.schedule(reminderItem)
                preferences.isNotificationSetBefore(true)
            }
        }
    }
}
