package com.aayar94.aquatick.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.aquatick.domain.usecase.GetUserNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserNameUseCase: GetUserNameUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUIState())
    var uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val name = getUserNameUseCase.invoke()
            _uiState.update {
                it.copy(name = name, greetings = getGreetings())
            }

        }
    }

    private fun getGreetings(): String {
        val currentTime = LocalTime.now()
        return when {
            currentTime.isBefore(LocalTime.NOON) -> "Good Morning"
            currentTime.isAfter(LocalTime.NOON) && currentTime.isBefore(
                LocalTime.of(
                    18,
                    0
                )
            ) -> "Good Afternoon"

            else -> "Good Evening"
        }
    }

}