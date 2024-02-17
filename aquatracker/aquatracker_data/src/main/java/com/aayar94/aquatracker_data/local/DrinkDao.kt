package com.aayar94.aquatracker_data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aayar94.aquatracker_data.local.entity.DrinkEntity
import com.aayar94.aquatracker_domain.model.DayTotalAmount
import kotlinx.coroutines.flow.Flow


@Dao
interface DrinkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIntakenDrink(drinkEntity: DrinkEntity)

    @Delete
    suspend fun deleteDrink(drinkEntity: DrinkEntity)

    @Query(
        "SELECT * FROM drinks_table " +
                "WHERE dayOfMonth= :day " +
                "AND month= :month " +
                "AND year= :year"
    )
    fun getDrinksForDate(day: Int, month: Int, year: Int): Flow<List<DrinkEntity>>

    @Query("SELECT * FROM drinks_table ORDER BY ID DESC")
    fun getLast3Object(): Flow<List<DrinkEntity>>

    @Query("DELETE FROM drinks_table")
    suspend fun deleteDatabase()

    @Query(
        "SELECT * FROM drinks_table WHERE year = :year" +
                " AND month = :month " +
                "AND dayOfMonth BETWEEN :startDay AND :endDay " +
                "ORDER BY year DESC, month DESC, dayOfMonth DESC, hour DESC, minute DESC, second DESC"
    )
    suspend fun getDrinksForSevenDays(
        startDay: Int,
        endDay: Int,
        month: Int,
        year: Int
    ): List<DrinkEntity>

    @Query("SELECT dayOfMonth, SUM(drinkAmount) as totalAmount FROM drinks_table WHERE (year = :year AND month = :month AND dayOfMonth >= :startDay) OR (year = :prevYear AND month = :prevMonth AND dayOfMonth >= :prevStartDay) GROUP BY dayOfMonth ORDER BY dayOfMonth DESC LIMIT 7")
    suspend fun getLast7DaysTotalAmount(
        year: Int,
        month: Int,
        startDay: Int,
        prevYear: Int,
        prevMonth: Int,
        prevStartDay: Int
    ): List<DayTotalAmount>

    @Query("SELECT * FROM drinks_table WHERE dayOfMonth = :day AND month = :month AND year = :year")
    fun getDrinkEntitiesForDay(day: Int, month: Int, year: Int): List<DrinkEntity>
}