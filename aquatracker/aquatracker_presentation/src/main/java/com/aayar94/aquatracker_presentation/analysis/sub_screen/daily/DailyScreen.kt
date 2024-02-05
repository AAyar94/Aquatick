package com.aayar94.aquatracker_presentation.analysis.sub_screen.daily

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart

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
            Chart(
                chart = lineChart(),
                model = uiState.value.list!!,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(),
                startAxis = rememberStartAxis(),
                bottomAxis = rememberBottomAxis(),
                isZoomEnabled = false
            )
        } else {
            CircularProgressIndicator()
        }
    }
}