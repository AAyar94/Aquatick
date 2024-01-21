package com.aayar94.aquatracker_domain.usecase

import com.aayar94.aquatracker_domain.repository.AquaTrackerRepository
import java.time.LocalDate

class CalculateTodaysIntakeUseCase(
    private val aquaTrackerRepository: AquaTrackerRepository
) {

    suspend operator fun invoke(localDate: LocalDate): Int {
        val response = aquaTrackerRepository.getDrinksForDate(localDate)
        var dailyAmount = 0
        response.collect { list ->
            list.forEach {
                dailyAmount += it.defaultAmount
            }
        }
        return dailyAmount
    }
}