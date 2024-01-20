package com.aayar94.aquatracker_data.repository

import com.aayar94.aquatracker_data.local.DrinkDao
import com.aayar94.aquatracker_data.mapper.toDrinkEntity
import com.aayar94.aquatracker_data.mapper.toScreenDrinkItem
import com.aayar94.aquatracker_domain.repository.AquaTrackerRepository
import com.aayar94.aquatracker_domain.usecase.ScreenDrinkItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class AquaTrackerRepositoryImpl(
    private val dao: DrinkDao
) : AquaTrackerRepository {
    override suspend fun insertDrink(drink: ScreenDrinkItem) {
        return dao.insertIntakenDrink(drink.toDrinkEntity())
    }

    override suspend fun deleteDrink(drink: ScreenDrinkItem) {
        return dao.deleteDrink(drink.toDrinkEntity())
    }

    override suspend fun getDrinksForDate(localDate: LocalDate): Flow<List<ScreenDrinkItem>> {
        return dao.getDrinksForDate(localDate.dayOfMonth, localDate.monthValue, localDate.year)
            .map { list ->
                list.map { it.toScreenDrinkItem() }
            }
    }

    override suspend fun getLast3Object(): Flow<List<ScreenDrinkItem>> {
        return dao.getLast3Object().map {
            it.map { it.toScreenDrinkItem() }
        }
    }
}