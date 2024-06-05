package com.flavorfusion.flavorfusion.cocktails.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.flavorfusion.flavorfusion.cocktails.presentation.model.DrinkModel

@Composable
fun DrinkItem(
    drink: DrinkModel,
    onDrinkClick: (DrinkModel) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onDrinkClick(drink) },
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xD3A7EAF3)
        )
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
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            modifier = modifier
                .padding(8.dp)
                .background(color = Color(0xD3A7EAF3))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DrinkItemPreview() {
    DrinkItem(
        drink = DrinkModel(
            drinkName = "Pikachu",
            drinkImage = "https://www.drawing123.com/wp-content/uploads/2021/10/pikachu-drawing-step-11.png",
            drinkId = "123"
        ),
        onDrinkClick = {}
    )
}