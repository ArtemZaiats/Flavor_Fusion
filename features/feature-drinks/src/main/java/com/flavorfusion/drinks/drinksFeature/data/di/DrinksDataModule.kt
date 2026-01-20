package com.flavorfusion.drinks.drinksFeature.data.di

import com.flavorfusion.drinks.drinksFeature.data.repository.DrinkDataRepository
import com.flavorfusion.drinks.drinksFeature.domain.repository.DrinkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DrinksDataModule {

    @Binds
    abstract fun bindsDrinksRepository(
        drinkDataRepository: DrinkDataRepository
    ): DrinkRepository
}