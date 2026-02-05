package com.flavorfusion.flavorfusion.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.flavorfusion.drinks.drinksFeature.presentation.DrinkDetailsScreen
import com.flavorfusion.drinks.drinksFeature.presentation.DrinkDetailsViewModel
import com.flavorfusion.drinks.drinksFeature.presentation.DrinkViewModel
import com.flavorfusion.drinks.drinksFeature.presentation.DrinksScreen

@Composable
fun DrinksNavHost(
    modifier: Modifier = Modifier,
//    navController: NavHostController
) {
    val navController = rememberNavController()
    val drinksViewModel: DrinkViewModel = hiltViewModel()
    val drinksState = drinksViewModel.drinks.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Route.DrinksScreen
    ) {
        composable<Route.DrinksScreen> {
            DrinksScreen(
                modifier = modifier,
                uiState = drinksState.value,
                onDrinkClick = {
                    navController.navigate(
                        Route.DrinkDetailsScreen(
                            drinkId = it.drinkId,
                            drinkName = it.drinkName,
                            drinkImage = it.drinkImage
                        )
                    )
                },
                onSearchClick = { drinksViewModel.getDrinkByName(it) }
            )
        }
        composable<Route.DrinkDetailsScreen> {
            val drinkDetailsViewModel: DrinkDetailsViewModel = hiltViewModel()
            val drinkDetails = drinkDetailsViewModel.drinkDetails.collectAsState()
            val args = it.toRoute<Route.DrinkDetailsScreen>()
            drinkDetailsViewModel.getDrinkDetails(args.drinkId)

            DrinkDetailsScreen(
                modifier = modifier,
                drinkName = args.drinkName,
                drinkImage = args.drinkImage,
                drinkDetailsState = drinkDetails.value,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}