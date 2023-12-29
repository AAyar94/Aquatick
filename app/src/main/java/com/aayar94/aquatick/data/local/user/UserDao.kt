package com.aayar94.aquatick.data.local.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aayar94.aquatick.data.model.user.UserDataModel

@Dao
interface UserDao {

    @Query("SELECT * FROM userdatamodel")
    suspend fun getUserData(): UserDataModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUserData(model: UserDataModel)
}