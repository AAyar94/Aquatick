package com.aayar94.aquatracker_domain.model

import java.util.Locale

sealed class DrinkType(val name: String) {
    object Glass_Of_Water : DrinkType("Glass_of_water")
    object Bottle_Of_Water : DrinkType("Bottle_of_water")
    object A_Cup_Of_Coffee : DrinkType("A_Cup_Of_Coffee")
    object Tea : DrinkType("Tea")
    object Herbal_Tea : DrinkType("Herbal_tea")
    object Carbonated_Drinks : DrinkType("Carbonated_drink")
    object Milk : DrinkType("Milk")
    object Smoothie : DrinkType("Smoothie")
    object Fruit_Juice : DrinkType("Fruit_juice")


    companion object {
        fun fromString(name: String): DrinkType {
            return when (name.lowercase(Locale.ROOT)) {
                "glass_of_water" -> Glass_Of_Water
                "bottle_of_water" -> Bottle_Of_Water
                "a_cup_of_coffee" -> A_Cup_Of_Coffee
                "tea" -> Tea
                "herbal_tea" -> Herbal_Tea
                "carbonated_drink" -> Carbonated_Drinks
                "milk" -> Milk
                "smoothie" -> Smoothie
                "fruit_juice" -> Fruit_Juice
                else -> Glass_Of_Water
            }
        }
    }
}