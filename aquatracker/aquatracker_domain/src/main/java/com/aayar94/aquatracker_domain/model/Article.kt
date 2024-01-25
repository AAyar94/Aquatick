package com.aayar94.aquatracker_domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val id: Int,
    val Conclusion: String,
    val Introduction: String,
    val Points: List<Point>,
    val Title: String,
    val image: String
) : Parcelable