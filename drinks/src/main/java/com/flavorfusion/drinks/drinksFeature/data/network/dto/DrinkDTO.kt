package com.flavorfusion.drinks.drinksFeature.data.network.dto

import com.google.gson.annotations.SerializedName

data class DrinkDTO(
    @SerializedName("strDrink") val drinkName: String,
    @SerializedName("strDrinkThumb") val drinkImage: String,
    @SerializedName("idDrink") val drinkId: String
)

data class DrinksDTOResponse(
    @SerializedName("drinks") val drinks: List<DrinkDTO>
)
