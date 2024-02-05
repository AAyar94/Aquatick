package com.aayar94.aquatracker_presentation.analysis.sub_screen.daily

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.m3.style.m3ChartStyle
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.chart.scale.AutoScaleUp

@Composable
fun DailyScreen(
    viewModel: DailyScreenViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    viewModel.getLastWeekDailyIntake()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (uiState.value.list != null) {
            ProvideChartStyle(m3ChartStyle()) {
                Chart(
                    chart = lineChart(),
                    model = uiState.value.list!!,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                        .padding(16.dp)
                        .align(Alignment.TopCenter),
                    startAxis = rememberStartAxis(),
                    bottomAxis = rememberBottomAxis(title = "Dates"),
                    isZoomEnabled = false,
                    autoScaleUp = AutoScaleUp.Full,
                )
            }
        } else {
            CircularProgressIndicator()
        }
    }
}