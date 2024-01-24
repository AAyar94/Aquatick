package com.aayar94.aquatick.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.ImageProvider
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.ContentScale
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.state.PreferencesGlanceStateDefinition
import com.aayar94.aquatick.MainActivity
import com.aayar94.aquatracker_domain.usecase.CalculateTodaysIntakeUseCase
import com.aayar94.core.domain.preferences.DefaultPreferences

class AquatrackWidget : GlanceAppWidget() {

    override val sizeMode: SizeMode = SizeMode.Exact
    override val stateDefinition: GlanceStateDefinition<*> = PreferencesGlanceStateDefinition


    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            val dailyIntakeAmount = remember { getMyPreferenceValue(context) }
            DailyIntakeStatus("250", dailyIntakeAmount = dailyIntakeAmount)
        }
    }

    private fun getMyPreferenceValue(context: Context): String {
        val sharedPreferences = context.getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
        val prefs = DefaultPreferences(sharedPreferences)
        return prefs.getUserInfo().dailyIntakeAmount.toString()
    }

    @Composable
    fun DailyIntakeStatus(
        currentIntakeStatus: String,
        dailyIntakeAmount: String
    ) {
        GlanceTheme {
            Box(
                modifier = GlanceModifier
                    .background(Color.White)
                    .fillMaxSize()
                    .padding(16.dp)
                    .cornerRadius(16.dp).clickable {
                        actionStartActivity<MainActivity>(MainActivity::class.java)
                    },
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = GlanceModifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    androidx.glance.layout.Column(
                        modifier = GlanceModifier.fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalAlignment = Alignment.Start
                    ) {
                        androidx.glance.text.Text(
                            //TODO get values from db with workmanager
                            text = "Your intake is now: $currentIntakeStatus ml"
                        )
                        Spacer(modifier = GlanceModifier.height(16.dp))
                        androidx.glance.text.Text(
                            //TODO read value from pref
                            text = "Your daily intake amount is : $dailyIntakeAmount ml"
                        )
                    }
                    androidx.glance.Image(
                        provider = ImageProvider(com.aayar94.core.R.drawable.water_intake_card_image),
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
    }
}