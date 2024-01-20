package com.aayar94.aquatracker_domain.repository

import com.aayar94.aquatracker_domain.usecase.ScreenDrinkItem
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface AquaTrackerRepository {

    suspend fun insertDrink(drink: ScreenDrinkItem)
    suspend fun deleteDrink(drink: ScreenDrinkItem)
    suspend fun getDrinksForDate(localDate: LocalDate): Flow<List<ScreenDrinkItem>>

    suspend fun getLast3Object(): Flow<List<ScreenDrinkItem>>
}