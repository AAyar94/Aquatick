package com.aayar94.aquatracker_presentation.analysis

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.aayar94.core_ui.theme.LocalSpacing
import kotlinx.coroutines.launch
import okhttp3.internal.wait


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnalysisScreen(
    viewModel: AnalysisViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    val spacing = LocalSpacing.current
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
    ) {
        TabRowItem.values().size
    }
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        TabRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(),
            selectedTabIndex = uiState.value.selectedTabState,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[uiState.value.selectedTabState])
                )
            }
        ) {
            TabRowItem.values().forEach { item ->
                Tab(
                    selected = uiState.value.selectedTabState == item.index,
                    text = { Text(text = item.text) },
                    icon = { Icon(item.icon, "") },
                    onClick = {
                        viewModel.updateTabState(item.index)
                        coroutineScope.launch { pagerState.scrollToPage(item.index) }
                    }

                )
            }
        }
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f),
            userScrollEnabled = false
        ) {
            TabRowItem.values()[pagerState.currentPage].screen()
        }
    }
}
