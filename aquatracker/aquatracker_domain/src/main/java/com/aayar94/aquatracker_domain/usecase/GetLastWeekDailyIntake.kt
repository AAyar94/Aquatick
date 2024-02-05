package com.aayar94.aquatracker_domain.usecase

import com.aayar94.aquatracker_domain.model.ChartModel
import com.aayar94.aquatracker_domain.repository.AquaTrackerRepository
import java.time.LocalDateTime
import javax.inject.Inject

class GetLastWeekDailyIntake @Inject constructor(
    private val repository: AquaTrackerRepository
) {

    suspend operator fun invoke(
        startDay: Int,
        endDay: Int,
        month: Int,
        year: Int
    ): List<ChartModel> {
        return repository.getDrinksForLastWeek(startDay, endDay, month, year)
    }

}