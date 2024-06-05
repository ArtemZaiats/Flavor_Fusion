package com.flavorfusion.flavorfusion

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.flavorfusion.flavorfusion.cocktails.presentation.DrinkDetailsScreen
import com.flavorfusion.flavorfusion.cocktails.presentation.DrinkDetailsViewModel
import com.flavorfusion.flavorfusion.cocktails.presentation.DrinkViewModel
import com.flavorfusion.flavorfusion.cocktails.presentation.DrinksScreen
import com.flavorfusion.flavorfusion.ui.theme.FlavorFusionTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlavorFusionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val drinksViewModel: DrinkViewModel = hiltViewModel()
                    val drinksState = drinksViewModel.cocktails.collectAsState()

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = DrinksScreenRoute
                    ) {
                        composable<DrinksScreenRoute> {
                            DrinksScreen(
                                modifier = Modifier.padding(innerPadding),
                                uiState = drinksState.value,
                                onDrinkClick = {
                                    navController.navigate(DrinkDetailsScreenRoute(it.drinkId))
                                    Log.i("MainActivity", "onDrinkClick: $it")
                                }
                            )
                        }
                        composable<DrinkDetailsScreenRoute> {
                            val drinkDetailsViewModel: DrinkDetailsViewModel = hiltViewModel()
                            val drinkDetails = drinkDetailsViewModel.drinkDetails.collectAsState()
                            val args = it.toRoute<DrinkDetailsScreenRoute>()
                            drinkDetailsViewModel.getDrinkDetails(args.drinkId)

                            DrinkDetailsScreen(
                                modifier = Modifier.padding(innerPadding),
                                drinkDetailsState = drinkDetails.value
                            )
                        }
                    }
                }
            }
        }
    }
}

@Serializable
object DrinksScreenRoute

@Serializable
data class DrinkDetailsScreenRoute(
    val drinkId: String
)