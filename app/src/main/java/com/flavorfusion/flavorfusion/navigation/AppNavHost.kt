package com.flavorfusion.flavorfusion.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.flavorfusion.flavorfusion.cocktails.presentation.DrinkDetailsScreen
import com.flavorfusion.flavorfusion.cocktails.presentation.DrinkDetailsViewModel
import com.flavorfusion.flavorfusion.cocktails.presentation.DrinkViewModel
import com.flavorfusion.flavorfusion.cocktails.presentation.DrinksScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val drinksViewModel: DrinkViewModel = hiltViewModel()
    val drinksState = drinksViewModel.drinks.collectAsState()

    SharedTransitionLayout {
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
                    animatedVisibilityScope = this
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
                    animatedVisibilityScope = this,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}