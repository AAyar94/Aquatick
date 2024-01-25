package com.aayar94.aquatracker_domain.usecase

import com.aayar94.aquatracker_domain.repository.AquaTrackerRepository
import kotlinx.coroutines.flow.first

class GetLastIntakeUseCase(
    private val repository: AquaTrackerRepository
) {

    suspend operator fun invoke(): ScreenDrinkItem? {
        val response = repository.getLast3Object().first()
        return if (response.isEmpty()) {
            null
        } else {
            response.first()
        }

    }
}