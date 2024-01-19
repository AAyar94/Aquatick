package com.aayar94.core.domain.preferences

import com.aayar94.core.domain.model.ActivityLevel
import com.aayar94.core.domain.model.Gender
import com.aayar94.core.domain.model.UserInfo

interface Preferences {
    fun saveName(name: String)
    fun saveGender(gender: Gender)
    fun saveAge(age: Int)
    fun saveWeight(weight: Float)
    fun saveActivityLevel(activityLevel: ActivityLevel)
    fun saveDailyIntakeAmount(amount: Int)

    fun saveOnboardingFinishedState(state: Boolean)
    fun loadOnboardingState(): Boolean

    fun saveGetUpTime(getUpTime:String)

    fun saveBedTime(bedTime:String)

    fun getUserInfo(): UserInfo

    companion object {
        const val KEY_NAME = "key_name"
        const val KEY_GENDER = "key_gender"
        const val KEY_AGE = "key_age"
        const val KEY_WEIGHT = "key_weight"
        const val KEY_ACTIVITY_LEVEL = "key_activity_level"
        const val KEY_DAILY_INTAKE_AMOUNT = "key_daily_intake_amount"
        const val KEY_ONBOARDING_FINISHED_STATE = "key_onboarding_finished_state"
        const val KEY_GET_UP_TIME="key_get_up_time"
        const val KEY_GOING_BED_TIME="key_going_bed_time"
    }
}