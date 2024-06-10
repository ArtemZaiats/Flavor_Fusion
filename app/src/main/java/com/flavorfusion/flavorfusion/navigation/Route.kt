package com.flavorfusion.flavorfusion.navigation

import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable data object DrinksScreen: Route()

    @Serializable data class DrinkDetailsScreen(
        val drinkId: String,
        val drinkName: String,
        val drinkImage: String
    ): Route()
}