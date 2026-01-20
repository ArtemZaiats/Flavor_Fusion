package com.flavorfusion.drinks.drinksFeature.domain.use_cases

import com.flavorfusion.drinks.drinksFeature.domain.model.DrinkDetails
import com.flavorfusion.drinks.drinksFeature.domain.repository.DrinkRepository
import javax.inject.Inject

class GetDrinkByIdUseCase @Inject constructor(
    private val drinkRepository: DrinkRepository,
) {

    suspend operator fun invoke(id: String): Result<List<DrinkDetails>> {
        return try {
            Result.success(drinkRepository.getDrinkById(id))
        } catch(e: Exception) {
            Result.failure(e)
        }
    }
}