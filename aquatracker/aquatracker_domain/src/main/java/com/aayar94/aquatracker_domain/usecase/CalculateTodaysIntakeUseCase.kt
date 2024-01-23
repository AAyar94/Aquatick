package com.aayar94.aquatracker_domain.usecase

import com.aayar94.aquatracker_domain.repository.AquaTrackerRepository
import kotlinx.coroutines.flow.first
import java.time.LocalDate

class CalculateTodaysIntakeUseCase(
    private val aquaTrackerRepository: AquaTrackerRepository
) {

    suspend operator fun invoke(localDate: LocalDate): Int {
        val response = aquaTrackerRepository.getDrinksForDate(localDate).first()
        return response.sumOf {
            it.defaultAmount
        }
    }
}