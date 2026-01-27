package com.flavorfusion.flavorfusion.di

import com.flavorfusion.drinks.di.DrinksModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        DrinksModule::class,
    ]
)
@InstallIn(SingletonComponent::class)
class AppModule