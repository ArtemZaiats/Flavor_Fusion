package com.flavorfusion.drinks.drinksFeature.data.utils

import com.flavorfusion.drinks.drinksFeature.data.network.dto.DrinkDTO
import com.flavorfusion.drinks.drinksFeature.data.network.dto.DrinkDetailsDTO
import com.flavorfusion.drinks.drinksFeature.domain.model.Drink
import com.flavorfusion.drinks.drinksFeature.domain.model.DrinkDetails

fun DrinkDTO.toDrink() = Drink(
    drinkName = drinkName,
    drinkImage = drinkImage,
    drinkId = drinkId
)

fun DrinkDetailsDTO.toDrinkDetails(): DrinkDetails {
    val ingredients = mutableMapOf<String?, String?>()

    val ingredientList = listOf(
        ingredient1, ingredient2, ingredient3, ingredient4, ingredient5,
        ingredient6, ingredient7, ingredient8, ingredient9, ingredient10,
        ingredient11, ingredient12, ingredient13, ingredient14, ingredient15
    )

    val measureList = listOf(
        measure1, measure2, measure3, measure4, measure5,
        measure6, measure7, measure8, measure9, measure10,
        measure11, measure12, measure13, measure14, measure15
    )

    for (i in ingredientList.indices) {
        val ingredient = ingredientList[i]
        val measure = measureList[i]
        if (!ingredient.isNullOrBlank() ) {
            ingredients[ingredient] = measure.orEmpty()
        }
    }

    return DrinkDetails(
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
        ingredients = ingredients.ifEmpty { null },
        imageSource = imageSource,
        imageAttribution = imageAttribution,
        creativeCommonsConfirmed = creativeCommonsConfirmed,
        dateModified = dateModified
    )
}