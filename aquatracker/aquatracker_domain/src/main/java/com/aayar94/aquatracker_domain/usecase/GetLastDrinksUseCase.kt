package com.aayar94.aquatracker_domain.usecase

import com.aayar94.aquatracker_domain.repository.AquaTrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetLastDrinksUseCase(
    private val repository: AquaTrackerRepository
) {
    suspend operator fun invoke() : Flow<List<ScreenDrinkItem>> {
        return repository.getLast3Object()
    }
}