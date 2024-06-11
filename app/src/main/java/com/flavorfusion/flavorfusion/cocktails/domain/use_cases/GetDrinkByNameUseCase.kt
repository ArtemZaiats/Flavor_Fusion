package com.flavorfusion.flavorfusion.cocktails.domain.use_cases

import com.flavorfusion.flavorfusion.cocktails.domain.model.Drink
import com.flavorfusion.flavorfusion.cocktails.domain.repository.DrinkRepository
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