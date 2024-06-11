package com.flavorfusion.flavorfusion.cocktails.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.flavorfusion.flavorfusion.cocktails.presentation.components.CocktailLoading
import com.flavorfusion.flavorfusion.cocktails.presentation.model.DrinkModel
import com.flavorfusion.flavorfusion.cocktails.presentation.model.UIState

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DrinksScreen(
    modifier: Modifier = Modifier,
    uiState: UIState,
    onDrinkClick: (DrinkModel) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope
) {

    DrinksList(
        modifier = modifier.background(color = Color.White),
        uiState = uiState,
        onDrinkClick = onDrinkClick,
        animatedVisibilityScope = animatedVisibilityScope
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DrinksList(
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
           LazyColumn(
               verticalArrangement = Arrangement.spacedBy(16.dp),
               modifier = modifier
                   .fillMaxSize()
                   .padding(8.dp),
               state = rememberLazyListState()
           ) {
               items(uiState.data as List<DrinkModel>) {
                   DrinkItem(
                       drink = it,
                       onDrinkClick = onDrinkClick,
                       animatedVisibilityScope = animatedVisibilityScope
                   )
               }
           }
       }

       is UIState.Error -> {
           //TODO
       }
   }
}