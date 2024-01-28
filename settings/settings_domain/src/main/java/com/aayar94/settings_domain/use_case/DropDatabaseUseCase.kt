package com.aayar94.settings_domain.use_case

import com.aayar94.aquatracker_domain.repository.AquaTrackerRepository

class DropDatabaseUseCase(
    val aquaTrackerRepository: AquaTrackerRepository
) {

    suspend operator fun invoke() {
        aquaTrackerRepository.deleteDatabase()
    }

}