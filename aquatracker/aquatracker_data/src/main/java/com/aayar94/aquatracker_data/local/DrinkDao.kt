package com.aayar94.aquatracker_data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aayar94.aquatracker_data.local.entity.DrinkEntity
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

    @Query("SELECT * FROM drinks_table ORDER BY ID DESC LIMIT 3")
    fun getLast3Object() : Flow<List<DrinkEntity>>

    @Query("DELETE FROM drinks_table")
    fun deleteDatabase()
}