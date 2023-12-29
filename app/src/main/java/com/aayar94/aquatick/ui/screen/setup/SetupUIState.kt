package com.aayar94.aquatick.ui.screen.setup

import com.aayar94.aquatick.core.base.BaseUiState

data class SetupUIState(
    override val isLoading: Boolean = true,
    override val error: String? = null,
    var name: String? = null,
    var age: Int? = 0,
    var weight: Int? = 0,
    var gender: String? = null,
    var activityLevel: String? = null
) : BaseUiState(isLoading, error)