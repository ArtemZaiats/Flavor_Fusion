package com.flavorfusion.common_ui.model.drink

import com.flavorfusion.common_domain.model.drinks.DrinkDetails

data class DrinkDetailsUi(
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

fun DrinkDetails.toUi() = DrinkDetails(
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

fun List<DrinkDetails>.toUi(): List<DrinkDetails> = map { it.toUi() }