package com.aayar94.aquatick.widget

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.glance.GlanceId
import androidx.glance.action.actionStartActivity
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import com.aayar94.aquatick.MainActivity

class AquatrackWidget() : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            DailyIntakeStatus("250 ml", "2250 ml")
        }
    }

    @Composable
    fun DailyIntakeStatus(
        currentIntakeStatus: String,
        dailyIntakeAmount: String
    ) {
        Box(modifier = Modifier
            .background(MaterialTheme.colors.background)
            .clickable {
                actionStartActivity(MainActivity::class.java)
            }) {
            Column {
                Text(
                    text = "Your intake is now: $currentIntakeStatus",
                    color = MaterialTheme.colors.onBackground
                )
                Text(
                    text = "Your Daily intake amount is : $dailyIntakeAmount",
                    color = MaterialTheme.colors.onBackground
                )
            }
        }
    }
}