package com.aayar94.aquatracker_presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.aquatracker_domain.usecase.CalculateTodaysIntakeUseCase
import com.aayar94.aquatracker_domain.usecase.GetArticlesUseCase
import com.aayar94.aquatracker_domain.usecase.GetLastIntakeUseCase
import com.aayar94.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    preferences: com.aayar94.core.domain.preferences.Preferences,
    private val getArticlesUseCase: GetArticlesUseCase,
    private val getTodaysIntake: CalculateTodaysIntakeUseCase,
    private val getLastIntakeUseCase: GetLastIntakeUseCase
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeUIState())
    val homeState = _homeState.asStateFlow()

    private val _articleState = MutableStateFlow(ArticleUIState())
    val articleState = _articleState.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val userInfo = preferences.getUserInfo()
        viewModelScope.launch {
            _homeState.update {
                it.copy(
                    greetings = getGreeting(),
                    name = userInfo.name,
                    gender = userInfo.gender,
                    currentIntake = null,
                    lastIntakeTime = null,
                    lastIntakeType = null
                )
            }
        }
        getArticles()
        getTodaysIntake()
        getLastIntake()
    }

    private fun getLastIntake() {
        viewModelScope.launch {
            val response = getLastIntakeUseCase.invoke()
            if (response != null) {
                val hour = if (response.localDate.hour < 10) {
                    "0${response.localDate.hour}"
                } else {
                    response.localDate.hour.toString()
                }

                val minute = if (response.localDate.minute < 10) {
                    "0${response.localDate.minute}"
                } else {
                    response.localDate.minute.toString()
                }
                _homeState.update {
                    it.copy(
                        lastIntakeTime = "$hour : $minute",
                        lastIntakeType = response.drinkType.name + " (${response.defaultAmount} ml)"
                    )
                }
            }
        }
    }

    private fun getGreeting(): String {
        return when (LocalTime.now().hour) {
            in 5..11 -> "Good morning"
            in 12..16 -> "Good afternoon"
            in 17..20 -> "Good evening"
            else -> "Good night"
        }
    }

    fun getTodaysIntake() {
        viewModelScope.launch {
            val response = getTodaysIntake.invoke(LocalDate.now())
            _homeState.update {
                it.copy(
                    currentIntake = response.toString()
                )
            }
        }
    }

    private fun getArticles() {
        viewModelScope.launch {
            val response = getArticlesUseCase.invoke()
            _articleState.update {
                it.copy(
                    articlesItem = response.articles.random()
                )
            }
        }

    }

    fun onEnterDrinkClick() {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.Success)
        }
    }
}