package com.flavorfusion.flavorfusion.cocktails.presentation

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.flavorfusion.flavorfusion.cocktails.presentation.model.DrinkModel
import com.flavorfusion.flavorfusion.cocktails.presentation.model.asPresentation

@Composable
fun DrinksScreen(
    modifier: Modifier = Modifier,
    uiState: DrinkUIState,
    onDrinkClick: (DrinkModel) -> Unit,
) {

    DrinksList(modifier = modifier, drinks = uiState.cocktails, onDrinkClick = onDrinkClick)
}

@Composable
fun DrinksList(
    modifier: Modifier = Modifier,
    drinks: List<DrinkModel>,
    onDrinkClick: (DrinkModel) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        state = rememberLazyListState()
    ) {
        items(drinks) {
            DrinkItem(drink = it, onDrinkClick = onDrinkClick)
        }
    }

}