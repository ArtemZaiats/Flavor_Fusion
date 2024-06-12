package com.flavorfusion.flavorfusion.cocktails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flavorfusion.flavorfusion.cocktails.domain.model.Drink
import com.flavorfusion.flavorfusion.cocktails.domain.model.DrinkDetails
import com.flavorfusion.flavorfusion.cocktails.domain.use_cases.GetDrinkByNameUseCase
import com.flavorfusion.flavorfusion.cocktails.domain.use_cases.GetDrinksByAlcoholicUseCase
import com.flavorfusion.flavorfusion.cocktails.presentation.model.Alcohol
import com.flavorfusion.flavorfusion.cocktails.presentation.model.UIState
import com.flavorfusion.flavorfusion.cocktails.presentation.model.asPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkViewModel @Inject constructor(
    private val getDrinksByAlcoholicUseCase: GetDrinksByAlcoholicUseCase,
    private val getDrinkByNameUseCase: GetDrinkByNameUseCase
) : ViewModel() {
    private val alcoholicDrinks = flow {
        emit(UIState.Loading)
        emit(getDrinksByAlcoholicUseCase(Alcohol.ALCOHOLIC.type).toUIState())
    }

    private val nonAlcoholicDrinks = flow {
        emit(UIState.Loading)
        emit(getDrinksByAlcoholicUseCase(Alcohol.NON_ALCOHOLIC.type).toUIState())
    }

    private val optionalAlcoholDrinks = flow {
        emit(UIState.Loading)
        emit(getDrinksByAlcoholicUseCase(Alcohol.OPTIONAL.type).toUIState())
    }

    private val _drinks = MutableStateFlow<UIState>(UIState.Loading)
    val drinks = _drinks.asStateFlow()
    
    init {
        viewModelScope.launch {
            combine(
                alcoholicDrinks,
                nonAlcoholicDrinks,
                optionalAlcoholDrinks
            ) { alcoholic, nonAlcoholic, optional ->
                val combinedDrinks = mutableListOf<DrinkDetails>()
                if (alcoholic is UIState.Success<*>) combinedDrinks.addAll(alcoholic.data as List<DrinkDetails>)
                if (nonAlcoholic is UIState.Success<*>) combinedDrinks.addAll(nonAlcoholic.data as List<DrinkDetails>)
                if (optional is UIState.Success<*>) combinedDrinks.addAll(optional.data as List<DrinkDetails>)
                UIState.Success(combinedDrinks)
            }.collect { combinedState ->
                _drinks.value = combinedState
            }
        }
    }

    fun getDrinkByName(name: String) {
        viewModelScope.launch {
            flow {
                emit(UIState.Loading)
                emit(getDrinkByNameUseCase(name).toUIState())
            }.collect {
                _drinks.value = it
            }
        }
    }
}

private fun Result<List<Drink>>.toUIState(): UIState {
    return fold(
        onSuccess = { drinks -> UIState.Success(drinks.map { it.asPresentation() }) },
        onFailure = { exception -> UIState.Error(exception.localizedMessage!!) }
    )
}