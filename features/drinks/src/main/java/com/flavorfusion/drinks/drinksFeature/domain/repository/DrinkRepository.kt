package com.flavorfusion.drinks.drinksFeature.domain.repository

import com.flavorfusion.drinks.drinksFeature.domain.model.Drink
import com.flavorfusion.drinks.drinksFeature.domain.model.DrinkDetails

interface DrinkRepository {
    suspend fun getDrinksByAlcoholic(alcoholic: String): List<Drink>

    suspend fun getDrinkById(id: String): List<DrinkDetails>

    suspend fun getDrinkByName(name: String): List<Drink>

}