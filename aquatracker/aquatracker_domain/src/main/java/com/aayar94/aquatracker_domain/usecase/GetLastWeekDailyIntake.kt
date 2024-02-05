package com.aayar94.aquatracker_domain.usecase

import com.aayar94.aquatracker_domain.model.ChartModel
import com.aayar94.aquatracker_domain.model.DayTotalAmount
import com.aayar94.aquatracker_domain.repository.AquaTrackerRepository
import java.time.LocalDateTime
import javax.inject.Inject

class GetLastWeekDailyIntake @Inject constructor(
    private val repository: AquaTrackerRepository
) {
    suspend operator fun invoke(
        year: Int,
        month: Int,
        startDay: Int,
        prevYear: Int,
        prevMonth: Int,
        prevStartDay: Int
    ): List<DayTotalAmount> {
        return repository.getLast7DaysTotalAmount(
            year,
            month,
            startDay,
            prevYear,
            prevMonth,
            prevStartDay
        )
    }
}