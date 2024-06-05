package com.flavorfusion.flavorfusion.cocktails.data.network.api

import com.flavorfusion.flavorfusion.cocktails.data.utils.Constants
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
    fun provideDrinksRetrofitClient(): DrinkApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DrinkApiService::class.java)
    }

}