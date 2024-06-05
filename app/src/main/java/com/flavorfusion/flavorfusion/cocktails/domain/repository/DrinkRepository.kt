package com.flavorfusion.flavorfusion.cocktails.domain.repository

import com.flavorfusion.flavorfusion.cocktails.domain.model.Drink
import com.flavorfusion.flavorfusion.cocktails.domain.model.DrinkDetails

interface DrinkRepository {
    suspend fun getDrinksByAlcoholic(alcoholic: String): List<Drink>

    suspend fun getDrinkById(id: String): List<DrinkDetails>

}