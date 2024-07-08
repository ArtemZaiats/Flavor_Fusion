package com.flavorfusion.drinks.drinksFeature.domain.use_cases

import com.flavorfusion.drinks.drinksFeature.domain.model.Drink
import com.flavorfusion.drinks.drinksFeature.domain.repository.DrinkRepository
import javax.inject.Inject

class GetDrinksByAlcoholicUseCase @Inject constructor(
    private val drinkRepository: DrinkRepository,
) {
    suspend operator fun invoke(alcoholic: String): Result<List<Drink>> {
        return try {
            Result.success(drinkRepository.getDrinksByAlcoholic(alcoholic))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}