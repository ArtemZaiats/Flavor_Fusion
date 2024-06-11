package com.flavorfusion.flavorfusion.cocktails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flavorfusion.flavorfusion.cocktails.domain.model.DrinkDetails
import com.flavorfusion.flavorfusion.cocktails.domain.use_cases.GetDrinkByIdUseCase
import com.flavorfusion.flavorfusion.cocktails.presentation.model.UIState
import com.flavorfusion.flavorfusion.cocktails.presentation.model.asPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkDetailsViewModel @Inject constructor(
    private val getDrinkByIdUseCase: GetDrinkByIdUseCase,
) : ViewModel() {

    private val _drinkDetails = MutableStateFlow<UIState>(UIState.Loading)
    val drinkDetails = _drinkDetails.asStateFlow()

    fun getDrinkDetails(drinkId: String) {
        if (_drinkDetails.value is UIState.Success<*>) return
        viewModelScope.launch(Dispatchers.IO) {
            flow {
                emit(UIState.Loading)
                emit(getDrinkByIdUseCase(drinkId).toUIState())
            }.collect {
                _drinkDetails.value = it
            }
        }
    }
}

private fun Result<List<DrinkDetails>>.toUIState(): UIState {
    return fold(
        onSuccess = { drinks -> UIState.Success(drinks.asPresentation()) },
        onFailure = { exception -> UIState.Error(exception.localizedMessage!!) }
    )
}