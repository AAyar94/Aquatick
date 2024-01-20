package com.aayar94.aquatracker_data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aayar94.aquatracker_data.local.entity.DrinkEntity

@Database(entities = [DrinkEntity::class], version = 1, exportSchema = false)
abstract class DrinkDatabase : RoomDatabase() {
    abstract val dao: DrinkDao
}