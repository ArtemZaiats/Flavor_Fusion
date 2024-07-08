package com.flavorfusion.drinks.drinksFeature.presentation.model

sealed class UIState {
    data object Loading : UIState()
    data class Success<T>(val data: T) : UIState()
    data class Error(val message: String) : UIState()

}