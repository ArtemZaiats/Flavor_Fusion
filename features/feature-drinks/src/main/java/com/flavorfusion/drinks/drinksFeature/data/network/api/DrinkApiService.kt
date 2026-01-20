package com.flavorfusion.drinks.drinksFeature.data.network.api

import com.flavorfusion.drinks.drinksFeature.data.network.dto.DrinkDetailsResponse
import com.flavorfusion.drinks.drinksFeature.data.network.dto.DrinksDTOResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinkApiService {

    @GET("filter.php")
    suspend fun getDrinksByAlcoholic(@Query("a") category: String): DrinksDTOResponse

    @GET("lookup.php")
    suspend fun getDrinkById(
        @Query("i") id: String
    ): DrinkDetailsResponse

    @GET("search.php")
    suspend fun getDrinksByName(
        @Query("s") name: String
    ): DrinksDTOResponse

}