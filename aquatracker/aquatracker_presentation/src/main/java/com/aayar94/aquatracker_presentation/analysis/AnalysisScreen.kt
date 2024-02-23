package com.aayar94.aquatracker_presentation.analysis

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aayar94.aquatracker_presentation.analysis.component.DaySelector
import com.aayar94.aquatracker_presentation.analysis.component.LastDrinksCard
import com.aayar94.core_ui.theme.LocalSpacing
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.compose.m3.style.m3ChartStyle
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.chart.scale.AutoScaleUp
import java.time.LocalDate


@Composable
fun AnalysisScreen(
    viewModel: AnalysisViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.getDrinksForDate(LocalDate.now())
        viewModel.getLastWeekDailyIntake()
    }
    val uiState = viewModel.uiState.collectAsState()
    val spacing = LocalSpacing.current
    val lazyColumnState = rememberLazyListState()
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Text(text = "Last Week Daily Amount")
            if (uiState.value.chartList != null) {
                ProvideChartStyle(m3ChartStyle()) {
                    Chart(
                        chart = columnChart(),
                        model = uiState.value.chartList!!,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.5f)
                            .padding(16.dp),
                        startAxis = rememberStartAxis(),
                        bottomAxis = rememberBottomAxis(title = "Dates"),
                        isZoomEnabled = false,
                        autoScaleUp = AutoScaleUp.Full,
                    )
                }
            }
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            DaySelector(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacing.spaceMedium),
                date = uiState.value.dateState,
                onPreviousDayClick = viewModel::onPreviousDayClick,
                onNextDayClick = viewModel::onNextDayClick
            )
            if (uiState.value.drinkList.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f), state = lazyColumnState
                ) {
                    items(uiState.value.drinkList, key = { it.idNumber!! }) { listItem ->
                        LastDrinksCard(item = listItem)
                        /*SwipeToDeleteContainer(item = listItem, onDelete = { deleteItem ->
                            viewModel.onDeleteItem(deleteItem.idNumber!!)
                        }) { item ->
                            LastDrinksCard(item = item)
                        }*/
                    }
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(imageVector = Icons.Default.Inbox, contentDescription = null)
                    Spacer(modifier = Modifier.height(spacing.spaceMedium))
                    Text(text = stringResource(id = com.aayar94.core.R.string.entry_not_found_for_this_date))
                }
            }
        }
    }
}