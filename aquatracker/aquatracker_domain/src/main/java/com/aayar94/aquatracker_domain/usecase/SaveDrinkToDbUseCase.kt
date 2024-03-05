package com.aayar94.aquatracker_domain.usecase

import com.aayar94.aquatracker_domain.model.ScreenDrinkItem
import com.aayar94.aquatracker_domain.repository.AquaTrackerRepository
import javax.inject.Inject

class SaveDrinkToDbUseCase @Inject constructor(
    private val repository: AquaTrackerRepository
) {

    suspend operator fun invoke(drink: ScreenDrinkItem) {
        return repository.insertDrink(drink)
    }

}