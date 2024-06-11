package com.flavorfusion.flavorfusion.cocktails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flavorfusion.flavorfusion.cocktails.domain.model.Drink
import com.flavorfusion.flavorfusion.cocktails.domain.use_cases.GetDrinksByAlcoholicUseCase
import com.flavorfusion.flavorfusion.cocktails.presentation.model.UIState
import com.flavorfusion.flavorfusion.cocktails.presentation.model.asPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DrinkViewModel @Inject constructor(
    private val getDrinksByAlcoholicUseCase: GetDrinksByAlcoholicUseCase,
) : ViewModel() {
    private val alcoholicDrinks = flow {
        emit(UIState.Loading)
        emit(getDrinksByAlcoholicUseCase("Alcoholic").toUIState())
    }

    private val nonAlcoholicDrinks = flow {
        emit(UIState.Loading)
        emit(getDrinksByAlcoholicUseCase("Non alcoholic").toUIState())
    }

    private val optionalAlcoholDrinks = flow {
        emit(UIState.Loading)
        emit(getDrinksByAlcoholicUseCase("Optional alcohol").toUIState())
    }

    val drinks: StateFlow<UIState> = merge(
        nonAlcoholicDrinks,
        optionalAlcoholDrinks,
        alcoholicDrinks
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UIState.Loading
    )
}

private fun Result<List<Drink>>.toUIState(): UIState {
    return fold(
        onSuccess = { drinks -> UIState.Success(drinks.map { it.asPresentation() }) },
        onFailure = { exception -> UIState.Error(exception.localizedMessage!!) }
    )
}