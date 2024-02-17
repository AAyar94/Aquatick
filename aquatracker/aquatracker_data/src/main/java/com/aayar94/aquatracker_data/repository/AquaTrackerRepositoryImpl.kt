package com.aayar94.aquatracker_data.repository

import com.aayar94.aquatracker_data.local.DrinkDao
import com.aayar94.aquatracker_data.mapper.toDrinkEntity
import com.aayar94.aquatracker_data.mapper.toScreenDrinkItem
import com.aayar94.aquatracker_domain.model.ChartModel
import com.aayar94.aquatracker_domain.model.DayTotalAmount
import com.aayar94.aquatracker_domain.repository.AquaTrackerRepository
import com.aayar94.aquatracker_domain.usecase.ScreenDrinkItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.LocalDateTime

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
        return dao.getLast3Object().map { drinkEntities ->
            drinkEntities.map { it.toScreenDrinkItem() }
        }
    }


    override suspend fun deleteDatabase() {
        return dao.deleteDatabase()
    }

    override suspend fun getDrinksForLastWeek(
        startDay: Int, endDay: Int, month: Int, year: Int
    ): List<ChartModel> {
        return dao.getDrinksForSevenDays(
            startDay = startDay, endDay = endDay, month = month, year = year
        ).map {
            ChartModel(
                LocalDateTime.of(
                    it.year,
                    it.month,
                    it.dayOfMonth,
                    it.hour,
                    it.minute,
                    it.second
                ),
                it.drinkAmount
            )
        }
    }

    override suspend fun getDrinkForDate(day: Int, month: Int, year: Int): List<ScreenDrinkItem> {
        return dao.getDrinkEntitiesForDay(day, month, year).map {
            it.toScreenDrinkItem()
        }
    }

    override suspend fun getLast7DaysTotalAmount(
        year: Int,
        month: Int,
        startDay: Int,
        prevYear: Int,
        prevMonth: Int,
        prevStartDay: Int
    ): List<DayTotalAmount> {
        return dao.getLast7DaysTotalAmount(
            year,
            month,
            startDay,
            prevYear,
            prevMonth,
            prevStartDay
        )
    }
}