package com.aayar94.aquatick.data.local.user

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aayar94.aquatick.data.model.user.UserDataModel

@Database(entities = [UserDataModel::class], version = 1, exportSchema = false)
abstract class UserDataDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}