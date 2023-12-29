package com.aayar94.aquatick.data.model.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserDataModel(
    @PrimaryKey(autoGenerate = false)
    val dbId: Int = 0,
    val age: Int = 0,
    val weight: Int = 0,
    val gender: String = "",
    val activityLevel: Int = 0
)