package com.zaiatsdevelopment.common_data.remote.model.drinks

import kotlinx.serialization.SerialName

data class DrinkDetailsResponseData(
    @SerialName("drinks") val drinksList: List<DrinkDetailsData>
)

fun DrinkDetailsResponseData.toDomain() = drinksList.map { it.toDomain() }