package com.aayar94.core.domain.model

sealed class ActivityLevel(val name: String) {
    object NotActive : ActivityLevel("not_active")
    object LessThanAverage : ActivityLevel("less_than_average")
    object Average : ActivityLevel("average")
    object Active : ActivityLevel("active")
    object VeryActive : ActivityLevel("very_active")

    companion object {
        fun fromString(name: String): ActivityLevel {
            return when (name) {
                "not_active" -> NotActive
                "less_than_average" -> LessThanAverage
                "average" -> Average
                "active" -> Active
                else -> VeryActive
            }
        }
    }
}