package com.aayar94.aquatick.ui.screen.onboarding

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.aayar94.aquatick.R
import com.aayar94.aquatick.core.base.BaseUiState

data class OnboardingUiState(
    val destinationRoute: String? = null,
    override val isLoading: Boolean = true,
    override val error: String? = null
) : BaseUiState(isLoading, error)


data class OnboardingDataModel(
    val image: Int,
    val title: Int,
    val text: Int,
    val backgroundColor: Color,
    val textColor: Color,
    val isLastPage:Boolean=false
) {

}

@Composable
fun getOnboardingDataList(): List<OnboardingDataModel> {
    return listOf(
        OnboardingDataModel(
            R.drawable.onboarding1,
            R.string.track_your_daily_water_intake_with_us,
            R.string.achieve_your_hydration_goals_with_a_simple_tap,
            MaterialTheme.colorScheme.background,
            Color.Black
        ),
        OnboardingDataModel(
            R.drawable.onboarding2,
            R.string.smart_reminders_taoiled_to_you,
            R.string.quick_and_easy_to_set_your_hydration_goal_then_track_your_daily_water_intake_progress,
            MaterialTheme.colorScheme.background,
            Color.Black
        ),
        OnboardingDataModel(
            R.drawable.onboarding3,
            R.string.easy_to_use_drink_tap_repeat,
            R.string.staying_hydrated_every_day_is_easy_with_drops_water_tracker,
            MaterialTheme.colorScheme.background,
            Color.Black
        ),
        OnboardingDataModel(
            R.drawable.man_drinking_water,
            R.string.before_you_go,
            R.string.we_need_permission,
            MaterialTheme.colorScheme.background,
            Color.Black,
            isLastPage = true
        )
    )
}