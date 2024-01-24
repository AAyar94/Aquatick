package com.aayar94.aquatracker_domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Point(
    val Point: String,
    val PointNumber: Int
) : Parcelable