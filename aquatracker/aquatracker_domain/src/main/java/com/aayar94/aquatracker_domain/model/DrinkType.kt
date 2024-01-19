package com.aayar94.aquatracker_domain.model

sealed class DrinkType(val name: String) {
    object GlassOfWater : DrinkType("glass_of_water")
    object BottleOfWater : DrinkType("bottle_of_water")
    object ACupOfCoffee : DrinkType("a_cup_of_coffee")
    object Tea : DrinkType("tea")
    object HerbalTea : DrinkType("herbal_tea")
    object CarbonatedDrinks : DrinkType("carbonated_drink")
    object Milk : DrinkType("milk")
    object Smoothie : DrinkType("smoothie")
    object FruitJuice : DrinkType("fruit_juice")


    companion object {
        fun fromString(name: String): DrinkType {
            return when (name.lowercase()) {
                "glass_of_water" -> GlassOfWater
                "bottle_of_water" -> BottleOfWater
                "a_cup_of_water" -> ACupOfCoffee
                "tea" -> Tea
                "herbal_tea" -> HerbalTea
                "carbonated_drink" -> CarbonatedDrinks
                "milk" -> Milk
                "smoothie" -> Smoothie
                "fruit_juice" -> FruitJuice
                else -> GlassOfWater
            }
        }
    }
}