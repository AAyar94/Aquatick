package com.aayar94.aquatick.ui.screen.setup

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SetupViewModel @Inject constructor() : ViewModel() {

    private var _uiState = MutableStateFlow(SetupUIState())
    val uiState = _uiState.asStateFlow()


    fun updateName(name: String) {
        _uiState.update {
            it.copy(name = name)
        }
    }

    fun updateAge(age: String) {
        _uiState.update {
            it.copy(age = age.toInt())
        }
    }

    fun updateWeight(weight: String) {
        _uiState.update {
            it.copy(weight = weight.toInt())
        }
    }

    fun updateGender(gender: String) {
        _uiState.update {
            it.copy(gender = gender)
        }
    }

    fun updateActivityLevel(ac: String) {
        _uiState.update {
            it.copy(activityLevel = ac)
        }
    }

    fun saveUserData(){

    }

}