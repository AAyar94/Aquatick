package com.aayar94.aquatracker_domain.usecase

import com.aayar94.aquatracker_domain.model.ScreenDrinkItem
import com.aayar94.aquatracker_domain.repository.AquaTrackerRepository
import java.time.LocalDate
import javax.inject.Inject

class GetDrinkForDateUseCase @Inject constructor(
    private val aquaTrackerRepository: AquaTrackerRepository
) {
    suspend operator fun invoke(localDate: LocalDate): List<ScreenDrinkItem> {
        return aquaTrackerRepository.getDrinkForDate(
            localDate.dayOfMonth,
            localDate.monthValue,
            localDate.year
        )
    }
}