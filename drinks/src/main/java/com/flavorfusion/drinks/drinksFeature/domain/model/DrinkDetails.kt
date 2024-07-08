package com.flavorfusion.drinks.drinksFeature.domain.model

data class DrinkDetails(
    val drinkId: String,
    val drinkName: String,
    val drinkAlternate: String?,
    val tags: String?,
    val videoUrl: String?,
    val category: String,
    val iba: String?,
    val alcoholic: String?,
    val glass: String?,
    val instructions: String?,
    val instructionsES: String?,
    val instructionsDE: String?,
    val instructionsFR: String?,
    val instructionsIT: String?,
    val instructionsZH_HANS: String?,
    val instructionsZH_HANT: String?,
    val drinkImage: String?,
    val ingredients: Map<String?, String?>?,
    val imageSource: String?,
    val imageAttribution: String?,
    val creativeCommonsConfirmed: String?,
    val dateModified: String?
)
