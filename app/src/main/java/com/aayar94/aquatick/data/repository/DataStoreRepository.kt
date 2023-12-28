package com.aayar94.aquatick.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.aayar94.aquatick.util.Constant.ONBOARDING_FINISHED_ONCE
import com.aayar94.aquatick.util.Constant.PREFERENCES_NAME
import com.aayar94.aquatick.util.Constant.SETUP_FINISHED_ONCE
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

@Singleton
class DataStoreRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private object PreferenceKeys {
        val onboardingPassedOnce = booleanPreferencesKey(ONBOARDING_FINISHED_ONCE)
        val setupFinished = booleanPreferencesKey(SETUP_FINISHED_ONCE)
    }

    private val dataStore: DataStore<Preferences> = context.datastore

    suspend fun saveOnboardingFinished(bool: Boolean) {
        dataStore.edit { preference ->
            preference[PreferenceKeys.onboardingPassedOnce] = bool
        }
    }

    val onboardingFinishedState: Flow<Boolean> = dataStore.data.map { preference ->
        val finishedOnceState = preference[PreferenceKeys.onboardingPassedOnce] ?: false
        try {
            finishedOnceState
        } catch (ex: IllegalArgumentException) {
            Log.e("OnboardingFinishedStateError", "Cant return flow")
            false
        }
    }

    suspend fun saveSetupFinished(bool: Boolean) {
        dataStore.edit { preference ->
            preference[PreferenceKeys.setupFinished] = bool
        }
    }

    val setupFinishedState: Flow<Boolean> = dataStore.data.map { preference ->
        val state = preference[PreferenceKeys.setupFinished] ?: false
        try {
            state
        } catch (e: java.lang.IllegalArgumentException) {
            Log.e("SetupError", e.message.toString())
            false
        }
    }
}