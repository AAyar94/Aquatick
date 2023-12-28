package com.aayar94.aquatick.ui.screen.onboarding

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.aquatick.data.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    fun onBoardingFinished() {
        viewModelScope.launch {
            dataStoreRepository.saveOnboardingFinished(false)
            Log.e("OnboardingSaved", "Onboarding finished")
        }
    }


}