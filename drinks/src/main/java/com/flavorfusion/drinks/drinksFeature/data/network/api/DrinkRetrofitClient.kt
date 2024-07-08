package com.flavorfusion.drinks.drinksFeature.data.network.api

import com.flavorfusion.drinks.drinksFeature.data.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DrinkRetrofitClient {

    private const val BASE_URL = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideDrinksRetrofitClient(): com.flavorfusion.drinks.drinksFeature.data.network.api.DrinkApiService {
        return Retrofit.Builder()
            .baseUrl(com.flavorfusion.drinks.drinksFeature.data.network.api.DrinkRetrofitClient.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(com.flavorfusion.drinks.drinksFeature.data.network.api.DrinkApiService::class.java)
    }

}