package com.aayar94.aquatracker_presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.aayar94.aquatracker_presentation.home.component.DailyGoalCard
import com.aayar94.aquatracker_presentation.home.component.DailyReadCard
import com.aayar94.aquatracker_presentation.home.component.HomeHeader
import com.aayar94.core.util.UiEvent
import com.aayar94.core_ui.theme.LocalShape
import com.aayar94.core_ui.theme.LocalSpacing
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(), onDrinkNavigateClick: () -> Unit
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = spacing.spaceMedium)
                )
            }
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            DailyGoalCard(
                currentIntake = viewModel.homeState.currentIntake!!,
                lastIntakeType = null,
                lastIntakeTime = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(spacing.spaceMedium),
                onDrinkClick = viewModel::onEnterDrinkClick
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            //DailyReadCard(imageUrl = , text = , onClick = { /*TODO*/ }, shape = )
        }
    }
}