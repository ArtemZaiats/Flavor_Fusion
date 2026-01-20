package com.flavorfusion.drinks.drinksFeature.domain.use_cases

import com.flavorfusion.drinks.drinksFeature.domain.model.Drink
import com.flavorfusion.drinks.drinksFeature.domain.repository.DrinkRepository
import javax.inject.Inject

class GetDrinkByNameUseCase @Inject constructor(
    private val drinkRepository: DrinkRepository,
) {

    suspend operator fun invoke(name: String): Result<List<Drink>> {
        return try {
            Result.success(drinkRepository.getDrinkByName(name))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}