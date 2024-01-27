package com.aayar94.aquatracker_presentation.analysis

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.aayar94.core_ui.theme.AquatickTheme
import com.aayar94.core_ui.theme.LocalSpacing
import com.aayar94.core_ui.util.DevicesPreview
import com.aayar94.core_ui.util.WorkInProgress

@Composable
fun AnalysisScreen(
    viewModel: AnalysisViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    val spacing = LocalSpacing.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        /*  Column(
              modifier = Modifier
                  .fillMaxSize()
                  .padding(spacing.spaceMedium),
              verticalArrangement = Arrangement.Center,
              horizontalAlignment = Alignment.CenterHorizontally
          ) {
              val modelProducer: CartesianChartModelProducer
              ProvideChartStyle(m3ChartStyle()) {
                  CartesianChartHost(
                      chart =
                      rememberCartesianChart(
                          rememberLineCartesianLayer(),
                          startAxis = rememberStartAxis(),
                          bottomAxis = rememberBottomAxis(guideline = null)
                      ),
                      modelProducer = modelProducer,
                      runInitialAnimation = false,
              }*/
        WorkInProgress(whatIsThisRoute = "Analysis Screen")
    }
}


@DevicesPreview
@Composable
fun PreviewAnalysisScreen() {
    AquatickTheme {
        AnalysisScreen()
    }
}