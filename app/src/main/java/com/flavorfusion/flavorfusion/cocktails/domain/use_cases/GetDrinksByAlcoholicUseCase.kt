package com.flavorfusion.flavorfusion.cocktails.domain.use_cases

import com.flavorfusion.flavorfusion.cocktails.domain.model.Drink
import com.flavorfusion.flavorfusion.cocktails.domain.repository.DrinkRepository
import javax.inject.Inject

class GetDrinksByAlcoholicUseCase @Inject constructor(
    private val drinkRepository: DrinkRepository,
) {
    suspend operator fun invoke(alcoholic: String): List<Drink> {
        return drinkRepository.getDrinksByAlcoholic(alcoholic)
    }

}