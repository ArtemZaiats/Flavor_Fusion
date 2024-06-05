package com.flavorfusion.flavorfusion.cocktails.data.repository

import android.util.Log
import com.flavorfusion.flavorfusion.cocktails.data.network.api.DrinkApiService
import com.flavorfusion.flavorfusion.cocktails.data.utils.toDrink
import com.flavorfusion.flavorfusion.cocktails.data.utils.toDrinkDetails
import com.flavorfusion.flavorfusion.cocktails.domain.model.Drink
import com.flavorfusion.flavorfusion.cocktails.domain.model.DrinkDetails
import com.flavorfusion.flavorfusion.cocktails.domain.repository.DrinkRepository
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

}



