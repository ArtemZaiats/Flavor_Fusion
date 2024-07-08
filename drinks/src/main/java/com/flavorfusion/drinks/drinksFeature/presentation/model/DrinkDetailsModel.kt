package com.flavorfusion.drinks.drinksFeature.presentation.model

import com.flavorfusion.drinks.drinksFeature.domain.model.DrinkDetails

data class DrinkDetailsModel(
    val drinkId: String = "",
    val drinkName: String = "",
    val drinkAlternate: String? = "",
    val tags: String? = "",
    val videoUrl: String? = "",
    val category: String = "",
    val iba: String? = "",
    val alcoholic: String? = "",
    val glass: String? = "",
    val instructions: String? = "",
    val instructionsES: String? = "",
    val instructionsDE: String? = "",
    val instructionsFR: String? = "",
    val instructionsIT: String? = "",
    val instructionsZH_HANS: String? = "",
    val instructionsZH_HANT: String? = "",
    val drinkImage: String? = "",
    val ingredients: Map<String?, String?>? = emptyMap(),
    val imageSource: String? = "",
    val imageAttribution: String? = "",
    val creativeCommonsConfirmed: String? = "",
    val dateModified: String? = ""
)

fun DrinkDetails.asPresentation() = DrinkDetailsModel(
    drinkId = drinkId,
    drinkName = drinkName,
    drinkAlternate = drinkAlternate,
    tags = tags,
    videoUrl = videoUrl,
    category = category,
    iba = iba,
    alcoholic = alcoholic,
    glass = glass,
    instructions = instructions,
    instructionsES = instructionsES,
    instructionsDE = instructionsDE,
    instructionsFR = instructionsFR,
    instructionsIT = instructionsIT,
    instructionsZH_HANS = instructionsZH_HANS,
    instructionsZH_HANT = instructionsZH_HANT,
    drinkImage = drinkImage,
    ingredients = ingredients,
    imageSource = imageSource,
    imageAttribution = imageAttribution,
    creativeCommonsConfirmed = creativeCommonsConfirmed,
    dateModified = dateModified,
)

fun List<DrinkDetails>.asPresentation(): List<DrinkDetailsModel> = map { it.asPresentation() }