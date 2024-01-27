package com.aayar94.aquatracker_domain.usecase

import com.aayar94.aquatracker_domain.model.ChartModel
import java.time.LocalDateTime

class AnalysisScreenChartCalculate {


    suspend fun invoke(): List<ChartModel> {
        val list = listOf(
            ChartModel(LocalDateTime.now().minusDays(1), 2000),
            ChartModel(LocalDateTime.now().minusDays(2), 2500),
            ChartModel(LocalDateTime.now().minusDays(3), 3000),
            ChartModel(LocalDateTime.now().minusDays(4), 1800),
            ChartModel(LocalDateTime.now().minusDays(5), 2100),
        )
        return list
    }
}