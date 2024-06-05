package com.flavorfusion.flavorfusion.cocktails.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.flavorfusion.flavorfusion.R
import com.flavorfusion.flavorfusion.cocktails.presentation.model.DrinkDetailsModel

@Composable
fun DrinkDetailsScreen(
    modifier: Modifier = Modifier,
    drinkDetailsState: DrinkDetailsUIState
) {
    DrinkBody(drinkDetailsState = drinkDetailsState)
}

@Composable
fun DrinkBody(modifier: Modifier = Modifier, drinkDetailsState: DrinkDetailsUIState) {

    val drink = drinkDetailsState.drinkDetails.getOrElse(0) { DrinkDetailsModel() }

    if (drinkDetailsState.isLoading) {
//        LoadingScreen()
        CocktailLoading()
    } else {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = drink.drinkImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f)
            )
            Text(
                text = drink.drinkName,
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                modifier = modifier
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = drink.category,
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 16.sp,
                    color = Color.Black
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .background(color = Color(0xFF6DE98C), shape = RoundedCornerShape(50.dp))
                    .padding(8.dp)
            )
        }
    }

}

@Composable
fun CocktailLoading() {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.cocktail_animation)
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LottieAnimation(
            composition = preloaderLottieComposition,
            modifier = Modifier.size(200.dp)
        )
    }
}
