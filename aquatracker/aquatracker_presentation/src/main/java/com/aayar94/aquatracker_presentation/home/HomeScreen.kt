package com.aayar94.aquatracker_presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aayar94.aquatracker_domain.model.Article
import com.aayar94.aquatracker_presentation.home.component.DailyGoalCard
import com.aayar94.aquatracker_presentation.home.component.DailyReadCard
import com.aayar94.aquatracker_presentation.home.component.HomeHeader
import com.aayar94.core.util.UiEvent
import com.aayar94.core_ui.theme.LocalShape
import com.aayar94.core_ui.theme.LocalSpacing
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onDrinkNavigateClick: () -> Unit,
    onArticleClick: (article: Article) -> Unit,
    onNotificationIconClick: () -> Unit
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
        viewModel.getTodaysIntake()
    }

    val uiState = viewModel.homeState.collectAsState()
    val articleState = viewModel.articleState.collectAsState()
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
            if (uiState.value.greetings!!.isNotBlank() && uiState.value.name!!.isNotBlank()) {
                HomeHeader(
                    greetings = uiState.value.greetings!!,
                    name = uiState.value.name!!,
                    isNewNotification = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = spacing.spaceMedium),
                    onNotificationButtonClick = onNotificationIconClick
                )
            }
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            if (uiState.value.currentIntake.isNullOrBlank()) {
                CircularProgressIndicator()
            } else {
                DailyGoalCard(
                    currentIntake = uiState.value.currentIntake!!,
                    lastIntakeType = null,
                    lastIntakeTime = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    shape = shapes.largeCornerRadius,
                    onDrinkClick = viewModel::onEnterDrinkClick
                )
            }
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            DailyReadCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(200.dp, 200.dp)
                    .wrapContentHeight(),
                imageUrl = articleState.value.articlesItem?.image,
                title = articleState.value.articlesItem?.Title ?: "",
                text = articleState.value.articlesItem?.Conclusion ?: "",
                shape = shapes.mediumCornerRadius,
                onClick = {
                    articleState.value.articlesItem?.let { onArticleClick(it) }
                }
            )
        }
    }
}

