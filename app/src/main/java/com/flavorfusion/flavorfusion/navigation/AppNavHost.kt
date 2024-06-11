package com.flavorfusion.flavorfusion.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    rootNavController: NavHostController
) {
    NavHost(navController = rootNavController, startDestination = "drinks") {
        composable("recipes") {
            RecipesNavHost(modifier = modifier)
        }
        composable("drinks") {
            DrinksNavHost(modifier = modifier)
        }
        composable("favorite") {
            FavoritesNavHost(modifier = modifier)
        }
    }
}