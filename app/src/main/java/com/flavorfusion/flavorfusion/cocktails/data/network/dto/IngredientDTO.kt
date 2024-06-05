package com.flavorfusion.flavorfusion.cocktails.data.network.dto

import com.google.gson.annotations.SerializedName

data class IngredientDTO(
    @SerializedName("strIngredient1") val ingredient: String
)

data class IngredientsResponse(
    @SerializedName("drinks") val ingredients: List<IngredientDTO>
)
