package com.flavorfusion.drinks.drinksFeature.data.repository

import com.flavorfusion.drinks.drinksFeature.data.network.api.DrinkApiService
import com.flavorfusion.drinks.drinksFeature.data.utils.toDrink
import com.flavorfusion.drinks.drinksFeature.data.utils.toDrinkDetails
import com.flavorfusion.drinks.drinksFeature.domain.model.Drink
import com.flavorfusion.drinks.drinksFeature.domain.model.DrinkDetails
import com.flavorfusion.drinks.drinksFeature.domain.repository.DrinkRepository
import javax.inject.Inject

class DrinkDataRepository @Inject constructor(private val drinkApiService: DrinkApiService) :
    DrinkRepository {

    override suspend fun getDrinksByAlcoholic(alcoholic: String): List<Drink> {
        return drinkApiService.getDrinksByAlcoholic(alcoholic).drinks.map { it.toDrink() }
    }

    override suspend fun getDrinkById(id: String): List<DrinkDetails> {
        val drinkDetails = drinkApiService.getDrinkById(id).drinkDetails
        return drinkDetails.map { it.toDrinkDetails() }
    }

    override suspend fun getDrinkByName(name: String): List<Drink> {
        return drinkApiService.getDrinksByName(name).drinks.map { it.toDrink() }
    }

}



