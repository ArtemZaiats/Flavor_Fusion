package com.flavorfusion.flavorfusion.cocktails.domain.use_cases

import android.util.Log
import com.flavorfusion.flavorfusion.cocktails.domain.model.DrinkDetails
import com.flavorfusion.flavorfusion.cocktails.domain.repository.DrinkRepository
import javax.inject.Inject

class GetDrinkByIdUseCase @Inject constructor(
    private val drinkRepository: DrinkRepository,
) {

    suspend operator fun invoke(id: String): List<DrinkDetails> {
        return drinkRepository.getDrinkById(id)
    }

}