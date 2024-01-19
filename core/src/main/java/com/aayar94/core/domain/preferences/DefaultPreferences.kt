package com.aayar94.core.domain.preferences

import android.content.SharedPreferences
import com.aayar94.core.domain.model.ActivityLevel
import com.aayar94.core.domain.model.Gender
import com.aayar94.core.domain.model.UserInfo

class DefaultPreferences(
    private val sharedPreferences: SharedPreferences
) : Preferences {
    override fun saveName(name: String) {
        sharedPreferences.edit()
            .putString(Preferences.KEY_NAME, name)
            .apply()
    }

    override fun saveGender(gender: Gender) {
        sharedPreferences.edit()
            .putString(Preferences.KEY_GENDER, gender.name)
            .apply()
    }

    override fun saveAge(age: Int) {
        sharedPreferences.edit()
            .putInt(Preferences.KEY_AGE, age)
            .apply()
    }

    override fun saveWeight(weight: Float) {
        sharedPreferences.edit()
            .putFloat(Preferences.KEY_WEIGHT, weight)
            .apply()
    }

    override fun saveActivityLevel(activityLevel: ActivityLevel) {
        sharedPreferences.edit()
            .putString(Preferences.KEY_ACTIVITY_LEVEL, activityLevel.name)
            .apply()
    }

    override fun saveDailyIntakeAmount(amount: Int) {
        sharedPreferences.edit()
            .putInt(Preferences.KEY_DAILY_INTAKE_AMOUNT, amount)
            .apply()
    }

    override fun saveOnboardingFinishedState(state: Boolean) {
        sharedPreferences.edit()
            .putBoolean(Preferences.KEY_ONBOARDING_FINISHED_STATE, state)
            .apply()
    }

    override fun loadOnboardingState(): Boolean {
        val state = sharedPreferences.getBoolean(Preferences.KEY_ONBOARDING_FINISHED_STATE, true)
        return state
    }

    override fun saveGetUpTime(getUpTime: String) {
        sharedPreferences.edit()
            .putString(Preferences.KEY_GET_UP_TIME, getUpTime)
            .apply()
    }

    override fun saveBedTime(bedTime: String) {
        sharedPreferences.edit()
            .putString(Preferences.KEY_GOING_BED_TIME, bedTime)
            .apply()
    }

    override fun getUserInfo(): UserInfo {
        val name = sharedPreferences.getString(Preferences.KEY_NAME, "")
        val age = sharedPreferences.getInt(Preferences.KEY_AGE, -1)
        val weight = sharedPreferences.getFloat(Preferences.KEY_WEIGHT, -1f)
        val genderString = sharedPreferences.getString(Preferences.KEY_GENDER, null)
        val activityLevelString = sharedPreferences
            .getString(Preferences.KEY_ACTIVITY_LEVEL, null)
        val dailyIntakeAmount = sharedPreferences.getInt(Preferences.KEY_DAILY_INTAKE_AMOUNT, 0)

        return UserInfo(
            name = name!!,
            gender = Gender.fromString(genderString!!),
            age = age,
            weight = weight,
            activityLevel = ActivityLevel.fromString(activityLevelString!!),
            dailyIntakeAmount = dailyIntakeAmount
        )
    }
}