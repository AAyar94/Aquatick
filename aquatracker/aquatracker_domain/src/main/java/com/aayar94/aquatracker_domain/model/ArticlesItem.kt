package com.aayar94.aquatracker_domain.model

data class ArticlesItem(
    val Conclusion: String,
    val Introduction: String,
    val Points: List<Point>,
    val Title: String
)