package com.aayar94.aquatick.ui.screen.setup

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SetupViewModel  @Inject constructor(): ViewModel() {

    private var _uiState = MutableStateFlow(SetupUIState())
     val uiState = _uiState.asStateFlow()



}