package com.flavorfusion.flavorfusion.cocktails.domain.use_cases

import androidx.compose.ui.window.isPopupLayout
import com.flavorfusion.flavorfusion.cocktails.domain.model.Drink
import com.flavorfusion.flavorfusion.cocktails.domain.repository.DrinkRepository
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