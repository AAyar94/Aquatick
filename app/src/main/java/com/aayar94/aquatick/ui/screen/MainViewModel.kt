package com.aayar94.aquatick.ui.screen

import androidx.lifecycle.ViewModel
import com.aayar94.aquatick.data.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    fun readOnboardingState(): Boolean {
        return runBlocking { dataStoreRepository.onboardingFinishedState.first() }
    }

}