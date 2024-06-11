package com.flavorfusion.flavorfusion.cocktails.presentation.model


sealed class UIState {
    data object Loading : UIState()
    data class Success<T>(val data: T) : UIState()
    data class Error(val message: String) : UIState()

}