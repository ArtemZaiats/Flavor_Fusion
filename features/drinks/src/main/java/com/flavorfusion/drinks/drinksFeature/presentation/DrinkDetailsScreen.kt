package com.flavorfusion.drinks.drinksFeature.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.flavorfusion.common_ui.compose.CocktailLoading
import com.flavorfusion.common_ui.theme.NunitoFontFontFamily
import com.flavorfusion.drinks.drinksFeature.presentation.model.DrinkDetailsModel
import com.flavorfusion.drinks.drinksFeature.presentation.model.UIState

@Composable
fun DrinkDetailsScreen(
    modifier: Modifier = Modifier,
    drinkName: String,
    drinkImage: String,
    drinkDetailsState: UIState,
    onBackClick: () -> Unit
) {
    DrinkBody(
        modifier = modifier,
        drinkDetailsState = drinkDetailsState,
        drinkName = drinkName,
        drinkImage = drinkImage,
        onBackClick = onBackClick
    )
}

@Composable
fun DrinkBody(
    modifier: Modifier = Modifier,
    drinkName: String,
    drinkImage: String,
    drinkDetailsState: UIState,
    onBackClick: () -> Unit
) {

    when (drinkDetailsState) {
        is UIState.Loading -> {
            CocktailLoading()
        }

        is UIState.Success<*> -> {
            val drink =
                (drinkDetailsState.data as List<DrinkDetailsModel>).getOrElse(0) { DrinkDetailsModel() }

            Column(
                modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Top
            ) {
                DrinkHeader(
                    drinkImage = drinkImage,
                    drinkName = drinkName,
                    drink = drink,
                    onBackClick = onBackClick
                )
                DrinkDetails(
                    modifier = modifier,
                    drink = drink
                )
                Spacer(modifier = Modifier.height(96.dp))
            }
        }

        is UIState.Error -> {
            //TODO
        }
    }
}

@Composable
fun DrinkHeader(
    modifier: Modifier = Modifier,
    drinkImage: String,
    drinkName: String,
    drink: DrinkDetailsModel,
    onBackClick: () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(color = Color.White)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = drinkImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(4 / 3.3f)
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
                    .size(32.dp)
                    .clip(shape = CircleShape)
                    .clickable { onBackClick() }
                    .background(color = Color.White, shape = CircleShape)
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = CircleShape
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                )
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(16.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                    )
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = drinkName,
                style = TextStyle(
                    fontFamily = NunitoFontFontFamily,
                    fontSize = 24.sp,
                    fontWeight = FontWeight(700),
                    color = Color.Black
                ),
                maxLines = 3,
                modifier = Modifier.fillMaxWidth(0.6f)
            )
            Text(
                text = drink.category,
                style = TextStyle(
                    fontFamily = NunitoFontFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .background(color = Color(0xFF81E6F3), shape = RoundedCornerShape(50.dp))
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun DrinkDetails(
    modifier: Modifier = Modifier,
    drink: DrinkDetailsModel,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        drink.ingredients?.forEach {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = it.key.orEmpty(),
                    style = TextStyle(
                        fontFamily = NunitoFontFontFamily,
                        fontSize = 18.sp,
                        fontWeight = FontWeight(600),
                        color = Color.Black
                    ),
                    modifier = Modifier.fillMaxWidth(0.5f)
                )
                Text(
                    text = it.value ?: "",
                    style = TextStyle(
                        fontFamily = NunitoFontFontFamily,
                        fontSize = 18.sp,
                        fontWeight = FontWeight(600),
                        color = Color.Black
                    ),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        Text(
            text = drink.instructions.orEmpty(),
            style = TextStyle(
                fontFamily = NunitoFontFontFamily,
                fontWeight = FontWeight(600),
                fontSize = 18.sp,
                color = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
    }
}