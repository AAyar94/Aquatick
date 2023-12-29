package com.aayar94.aquatick.ui.screen.setup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.aquatick.domain.model.SetupScreenModel
import com.aayar94.aquatick.domain.usecase.GetUserDataUserCase
import com.aayar94.aquatick.domain.usecase.UpdateUserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetupViewModel @Inject constructor(
    private val getUserDataUserCase: GetUserDataUserCase,
    private val updateUserDataUseCase: UpdateUserDataUseCase
) : ViewModel() {

    private var _uiState = MutableStateFlow(SetupUIState())
    val uiState = _uiState.asStateFlow()

    fun getUserData() {
        viewModelScope.launch {
            val response = getUserDataUserCase.invoke()
            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = null,
                    name = response.name,
                    age = response.age,
                    weight = response.weight,
                    gender = response.gender,
                    activityLevel = response.activityLevel

                )
            }
        }

    }

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

    fun saveUserData() {
        viewModelScope.launch {
            updateUserDataUseCase.invoke(
                SetupScreenModel(
                    name = uiState.value.name,
                    age = uiState.value.age,
                    weight = uiState.value.weight,
                    gender = uiState.value.gender,
                    activityLevel = uiState.value.activityLevel
                )
            )
        }
    }
}