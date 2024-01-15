package com.aayar94.core.domain.model

sealed class Gender(val name: String) {
    object Male : Gender("male")
    object Female : Gender("female")
    object LGBTQ : Gender("lgbtq")

    companion object {
        fun fromString(name: String): Gender {
            return when (name) {
                "male" -> Male
                "female" -> Female
                else -> LGBTQ
            }
        }
    }
}