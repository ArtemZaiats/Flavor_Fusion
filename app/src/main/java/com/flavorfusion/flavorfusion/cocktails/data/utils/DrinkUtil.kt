package com.flavorfusion.flavorfusion.cocktails.data.utils

import com.flavorfusion.flavorfusion.cocktails.data.network.dto.DrinkDTO
import com.flavorfusion.flavorfusion.cocktails.data.network.dto.DrinkDetailsDTO
import com.flavorfusion.flavorfusion.cocktails.domain.model.Drink
import com.flavorfusion.flavorfusion.cocktails.domain.model.DrinkDetails

fun DrinkDTO.toDrink() = Drink(
    drinkName = drinkName,
    drinkImage = drinkImage,
    drinkId = drinkId
)

fun DrinkDetailsDTO.toDrinkDetails() = DrinkDetails(
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
    ingredient1 = ingredient1,
    ingredient2 = ingredient2,
    ingredient3 = ingredient3,
    ingredient4 = ingredient4,
    ingredient5 = ingredient5,
    ingredient6 = ingredient6,
    ingredient7 = ingredient7,
    ingredient8 = ingredient8,
    ingredient9 = ingredient9,
    ingredient10 = ingredient10,
    ingredient11 = ingredient11,
    ingredient12 = ingredient12,
    ingredient13 = ingredient13,
    ingredient14 = ingredient14,
    ingredient15 = ingredient15,
    measure1 = measure1,
    measure2 = measure2,
    measure3 = measure3,
    measure4 = measure4,
    measure5 = measure5,
    measure6 = measure6,
    measure7 = measure7,
    measure8 = measure8,
    measure9 = measure9,
    measure10 = measure10,
    measure11 = measure11,
    measure12 = measure12,
    measure13 = measure13,
    measure14 = measure14,
    measure15 = measure15,
    imageSource = imageSource,
    imageAttribution = imageAttribution,
    creativeCommonsConfirmed = creativeCommonsConfirmed,
    dateModified = dateModified,
)