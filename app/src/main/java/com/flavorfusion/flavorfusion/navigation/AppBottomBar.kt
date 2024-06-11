package com.flavorfusion.flavorfusion.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.flavorfusion.flavorfusion.R

@Composable
fun AppBottomBar(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    NavigationBar(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .clip(shape = RoundedCornerShape(50.dp))
            .height(72.dp),
        containerColor = Color.Black,
        windowInsets = NavigationBarDefaults.windowInsets.only(WindowInsetsSides.Horizontal)
    ) {
        bottomNavigationItems.forEach { item ->
            val isSelected = navBackStackEntry?.destination?.route == item.title.lowercase()

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(item.title.lowercase()) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        tint = if (isSelected) {
                            Color(0xFF5770FF)
                        } else {
                            Color.Gray
                        }
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        color = if (isSelected) {
                            Color(0xFF5770FF)
                        } else {
                            Color.Gray
                        }
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}


data class BottomNavigationItem(
    val title: String,
    val icon: Int
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        title = "Recipes",
        icon = R.drawable.ic_food_navbar
    ),
    BottomNavigationItem(
        title = "Drinks",
        icon = R.drawable.ic_cocktail_navbar
    ),
    BottomNavigationItem(
        title = "Favorite",
        icon = R.drawable.ic_favorite_navbar
    )
)
