package com.flavorfusion.flavorfusion.cocktails.data.network.api

import com.flavorfusion.flavorfusion.cocktails.data.network.dto.DrinkDetailsResponse
import com.flavorfusion.flavorfusion.cocktails.data.network.dto.DrinksDTOResponse
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path
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