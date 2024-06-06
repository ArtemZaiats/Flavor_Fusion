package com.flavorfusion.flavorfusion.cocktails.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTooltipState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DrinkDetailsScreen(
    modifier: Modifier = Modifier,
    drinkName: String,
    drinkImage: String,
    drinkDetailsState: DrinkDetailsUIState,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    DrinkBody(
        drinkDetailsState = drinkDetailsState,
        drinkName = drinkName,
        drinkImage = drinkImage,
        animatedVisibilityScope = animatedVisibilityScope
    )
}

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SharedTransitionScope.DrinkBody(
    modifier: Modifier = Modifier,
    drinkName: String,
    drinkImage: String,
    drinkDetailsState: DrinkDetailsUIState,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val drink = drinkDetailsState.drinkDetails.getOrElse(0) { DrinkDetailsModel() }

    if (drinkDetailsState.isLoading) {
        CocktailLoading()
    } else {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            AsyncImage(
                model = drinkImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(4 / 3f)
                    .sharedElement(
                        state = rememberSharedContentState(key = "image/$drinkImage"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 500)
                        }
                    )
            )
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
            ) {
                Text(
                    text = drinkName,
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                    maxLines = 3,
                    modifier = modifier
                        .sharedElement(
                            state = rememberSharedContentState(key = "text/${drink.drinkName}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                        )
                )
                Text(
                    text = drink.category,
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .padding(8.dp)
                        .background(color = Color(0xFF6DE98C), shape = RoundedCornerShape(50.dp))
                        .padding(8.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = drink.instructions.orEmpty(),
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 18.sp,
                    color = Color.Black
                ),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
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
