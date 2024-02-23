package com.aayar94.aquatracker_domain.repository

import com.aayar94.aquatracker_domain.model.ChartModel
import com.aayar94.aquatracker_domain.model.DayTotalAmount
import com.aayar94.aquatracker_domain.model.ScreenDrinkItem
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface AquaTrackerRepository {

    suspend fun insertDrink(drink: ScreenDrinkItem)
    suspend fun deleteDrink(drink: ScreenDrinkItem)
    suspend fun getDrinksForDate(localDate: LocalDate): Flow<List<ScreenDrinkItem>>

    suspend fun getLast3Object(): Flow<List<ScreenDrinkItem>>

    suspend fun deleteDatabase()

    suspend fun getDrinksForLastWeek(
        startDay: Int,
        endDay: Int,
        month: Int,
        year: Int
    ): List<ChartModel>

    suspend fun getDrinkForDate(day: Int, month: Int, year: Int): List<ScreenDrinkItem>

    suspend fun getLast7DaysTotalAmount(
        year: Int,
        month: Int,
        startDay: Int,
        prevYear: Int,
        prevMonth: Int,
        prevStartDay: Int
    ): List<DayTotalAmount>
}