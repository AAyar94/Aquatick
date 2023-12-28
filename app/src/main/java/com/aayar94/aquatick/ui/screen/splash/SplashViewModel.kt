package com.aayar94.aquatick.ui.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.aquatick.data.repository.DataStoreRepository
import com.aayar94.aquatick.ui.screen.Onboarding
import com.aayar94.aquatick.ui.screen.home.Home
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SplashUIState())
    val uiState = _uiState.asStateFlow()


    fun readOnboardingStatus() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.onboardingFinishedState.collect {
                _uiState.value = SplashUIState(
                    isLoading = false,
                    route = if (it == true) Home.route else Onboarding.route
                )
            }
        }
    }

}