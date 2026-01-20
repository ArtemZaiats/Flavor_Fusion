package com.flavorfusion.drinks.drinksFeature.presentation.model

import com.flavorfusion.drinks.drinksFeature.domain.model.Drink

data class DrinkModel(
    val drinkName: String,
    val drinkImage: String,
    val drinkId: String
)

fun Drink.asPresentation() = DrinkModel(
    drinkName = drinkName,
    drinkImage = drinkImage,
    drinkId = drinkId
)

fun List<Drink>.asPresentation(): List<DrinkModel> = map { it.asPresentation() }