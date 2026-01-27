package com.flavorfusion.drinks.drinksFeature.presentation.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flavorfusion.common_ui.compose.CocktailLoading
import com.flavorfusion.drinks.drinksFeature.presentation.model.DrinkModel
import com.flavorfusion.drinks.drinksFeature.presentation.model.UIState

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DrinksGrid(
    modifier: Modifier = Modifier,
    uiState: UIState,
    onDrinkClick: (DrinkModel) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope
) {

    when (uiState) {
        is UIState.Loading -> {
            CocktailLoading()
        }

        is UIState.Success<*> -> {
            val data = (uiState.data as List<DrinkModel>)
            LazyVerticalGrid(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                columns = GridCells.Fixed(2),
                modifier = modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
            ) {
                items(data) {
                    DrinkItem(
                        drink = it,
                        onDrinkClick = onDrinkClick,
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(96.dp))
                }
            }
        }

        is UIState.Error -> {
            //TODO
        }
    }
}