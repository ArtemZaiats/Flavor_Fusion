package com.flavorfusion.common_domain.repositories

import com.flavorfusion.common_domain.model.drinks.Drink
import com.flavorfusion.common_domain.model.drinks.DrinkDetails
import com.flavorfusion.common_domain.model.Result

interface DrinksRepository {
    suspend fun getDrinksByAlcoholic(alcoholic: String): Result<List<Drink>?>
    suspend fun getDrinkById(id: String): Result<List<DrinkDetails>?>
    suspend fun getDrinkByName(name: String): Result<List<Drink>?>
}