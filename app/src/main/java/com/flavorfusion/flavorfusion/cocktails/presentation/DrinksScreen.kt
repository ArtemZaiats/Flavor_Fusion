package com.flavorfusion.flavorfusion.cocktails.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.flavorfusion.flavorfusion.cocktails.presentation.model.DrinkModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DrinksScreen(
    modifier: Modifier = Modifier,
    uiState: DrinkUIState,
    onDrinkClick: (DrinkModel) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope
) {

    DrinksList(
        modifier = modifier.background(color = Color.White),
        drinks = uiState.cocktails,
        onDrinkClick = onDrinkClick,
        animatedVisibilityScope = animatedVisibilityScope
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DrinksList(
    modifier: Modifier = Modifier,
    drinks: List<DrinkModel>,
    onDrinkClick: (DrinkModel) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        state = rememberLazyListState()
    ) {
        items(drinks) {
            DrinkItem(drink = it, onDrinkClick = onDrinkClick, animatedVisibilityScope = animatedVisibilityScope)
        }
    }

}