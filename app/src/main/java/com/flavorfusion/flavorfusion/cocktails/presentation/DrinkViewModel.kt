package com.flavorfusion.flavorfusion.cocktails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flavorfusion.flavorfusion.cocktails.domain.use_cases.GetDrinkByIdUseCase
import com.flavorfusion.flavorfusion.cocktails.domain.use_cases.GetDrinksByAlcoholicUseCase
import com.flavorfusion.flavorfusion.cocktails.presentation.model.DrinkDetailsModel
import com.flavorfusion.flavorfusion.cocktails.presentation.model.DrinkModel
import com.flavorfusion.flavorfusion.cocktails.presentation.model.asPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkViewModel @Inject constructor(
    private val getDrinksByAlcoholicUseCase: GetDrinksByAlcoholicUseCase,
) : ViewModel() {

    val cocktails: StateFlow<DrinkUIState> = flow {
        emit(getDrinksByAlcoholicUseCase("Alcoholic"))
    }.map {
        DrinkUIState(it.asPresentation())
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DrinkUIState()
    )
}

data class DrinkUIState(val cocktails: List<DrinkModel> = listOf())