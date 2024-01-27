package com.aayar94.aquatracker_domain.usecase

import com.aayar94.aquatracker_domain.repository.AquaTrackerRepository

class DropDatabaseUseCase(
    val aquaTrackerRepository: AquaTrackerRepository
) {

    suspend operator fun invoke() {
        aquaTrackerRepository.deleteDatabase()
    }

}