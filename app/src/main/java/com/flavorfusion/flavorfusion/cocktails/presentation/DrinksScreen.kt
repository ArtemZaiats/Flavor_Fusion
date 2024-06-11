package com.flavorfusion.flavorfusion.cocktails.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.flavorfusion.flavorfusion.R
import com.flavorfusion.flavorfusion.cocktails.presentation.components.CocktailLoading
import com.flavorfusion.flavorfusion.cocktails.presentation.model.DrinkModel
import com.flavorfusion.flavorfusion.cocktails.presentation.model.UIState

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DrinksScreen(
    modifier: Modifier = Modifier,
    uiState: UIState,
    onDrinkClick: (DrinkModel) -> Unit,
    onSearchClick: (String) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {
        SearchPanel(onSearchClick = onSearchClick)
        DrinksList(
            uiState = uiState,
            onDrinkClick = onDrinkClick,
            animatedVisibilityScope = animatedVisibilityScope
        )
    }
}

@Composable
fun SearchPanel(
    modifier: Modifier = Modifier,
    onSearchClick: (String) -> Unit,
) {
    val (text, setText) = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = text,
            onValueChange = setText,
            placeholder = { Text("Search drinks") },
            shape = RoundedCornerShape(16.dp),
            textStyle = TextStyle(
                color = Color.Black
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search icon",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Black
                )
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    if (text.isNotEmpty()) {
                        focusManager.clearFocus()
                        onSearchClick(text)
                        setText("")
                    }
                }
            ),
            singleLine = true,
            modifier = Modifier
                .weight(1f)
                .height(54.dp)
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(16.dp),
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF35F8E6),
                            Color(0xFF57F55E),
                            Color.Blue
                        )
                    )
                )
        )

        TextButton(
            onClick = {
                if (text.isNotEmpty()) {
                    focusManager.clearFocus()
                    onSearchClick(text)
                    setText("")
                } else {
                    focusManager.clearFocus()
                }
            },
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(
                text = stringResource(R.string.search),

                )
        }
    }
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
                    .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                state = rememberLazyListState()
            ) {
                items(uiState.data as List<DrinkModel>) {
                    DrinkItem(
                        drink = it,
                        onDrinkClick = onDrinkClick,
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }

        is UIState.Error -> {
            //TODO
        }
    }
}