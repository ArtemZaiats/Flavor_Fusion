package com.flavorfusion.flavorfusion.cocktails.presentation

import android.util.Log
import android.view.View
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flavorfusion.flavorfusion.DrinkDetailsScreenRoute
import com.flavorfusion.flavorfusion.cocktails.domain.use_cases.GetDrinkByIdUseCase
import com.flavorfusion.flavorfusion.cocktails.presentation.model.DrinkDetailsModel
import com.flavorfusion.flavorfusion.cocktails.presentation.model.asPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DrinkDetailsViewModel @Inject constructor(
    private val getDrinkByIdUseCase: GetDrinkByIdUseCase,
) : ViewModel() {

    private val _drinkDetails = MutableStateFlow(DrinkDetailsUIState())
    val drinkDetails = _drinkDetails.asStateFlow()

    fun getDrinkDetails(drinkId: String) {
        if (_drinkDetails.value.isLoaded) return
        UIState.Loading
        try {
            _drinkDetails.update {
                it.copy(isLoading = true)
            }
            viewModelScope.launch {
                val drink = withContext(Dispatchers.IO) {
                    getDrinkByIdUseCase(drinkId)
                }
                _drinkDetails.update {
                    it.copy(
                        drinkDetails = drink.asPresentation(),
                        isLoading = false,
                        isLoaded = true
                    )
                }
            }
        } catch (e: Exception) {
            _drinkDetails.update {
                it.copy(
                    isLoading = false,
                    error = e.message.toString()
                )
            }
        }
    }
}

data class DrinkDetailsUIState(
    val drinkDetails: List<DrinkDetailsModel> = listOf(),
    val isLoading: Boolean = false,
    val isLoaded: Boolean = false,
    val error: String = "",
)

sealed class UIState<T> {
    data object Loading : UIState<Nothing>()
    data class Error(val message: String) : UIState<Nothing>()
    data class Success(val data: List<DrinkDetailsModel>) : UIState<DrinkUIState>()

}