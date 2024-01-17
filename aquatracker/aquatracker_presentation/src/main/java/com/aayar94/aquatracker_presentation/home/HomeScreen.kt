package com.aayar94.aquatracker_presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aayar94.aquatracker_presentation.home.component.DailyGoalCard
import com.aayar94.aquatracker_presentation.home.component.HomeHeader
import com.aayar94.core.util.UiEvent
import com.aayar94.core_ui.theme.LocalShape
import com.aayar94.core_ui.theme.LocalSpacing
import com.aayar94.core_ui.util.WorkInProgress
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onDrinkNavigateClick: () -> Unit
) {
    val spacing = LocalSpacing.current
    val shapes = LocalShape.current
    val systemUIController = rememberSystemUiController()
    systemUIController.setStatusBarColor(MaterialTheme.colorScheme.background)
    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onDrinkNavigateClick()
                else -> Unit
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.spaceMedium),
            verticalArrangement = Arrangement.spacedBy(spacing.spaceMedium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (viewModel.homeState.greetings!!.isNotBlank() && viewModel.homeState.name!!.isNotBlank()) {
                HomeHeader(
                    greetings = viewModel.homeState.greetings!!,
                    name = viewModel.homeState.name!!,
                    isNewNotification = false,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = spacing.spaceMedium)
                )
            }
            DailyGoalCard(
                currentIntake = viewModel.homeState.currentIntake!!,
                dailyIntakeAmount = viewModel.homeState.dailyIntakeAmount!!,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(spacing.spaceMedium),
                onDrinkClick = viewModel::onEnterDrinkClick
            )
        }
    }
}