package com.aayar94.aquatracker_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aayar94.aquatracker_domain.model.DrinkType

@Entity(tableName = "drinks_table")
data class DrinkEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val drinkType: String,
    val drinkAmount: Int,
    val hour: Int,
    val minute: Int,
    val second: Int,
    val dayOfMonth: Int,
    val month: Int,
    val year: Int
)