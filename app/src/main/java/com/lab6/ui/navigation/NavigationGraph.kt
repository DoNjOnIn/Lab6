package com.lab6.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lab6.ui.screens.current.WeatherScreen
import com.lab6.ui.screens.current.WeatherScreenViewModel
import com.lab6.ui.screens.manu.MenuScreen
import org.koin.androidx.compose.getViewModel


const val SCREEN_MENU_SCREEN = "menuScreen"
const val SCREEN_WEATHER_SCREEN = "weatherScreen"


@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: WeatherScreenViewModel = getViewModel() // Use ViewModel to fetch items
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = SCREEN_MENU_SCREEN
    ) {
        // Menu Screen
        composable(
            route = SCREEN_MENU_SCREEN
        ) {
            MenuScreen(
                items = viewModel.getItemsList(),
                onWeather = { id -> navController.navigate("$SCREEN_WEATHER_SCREEN/$id") }
            )
        }

        // Weather Screen
        composable(
            route = "$SCREEN_WEATHER_SCREEN/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    nullable = false
                },
            )
        ) { backStackEntry ->
            val cityId = backStackEntry.arguments?.getInt("id") ?: -1
            WeatherScreen(id = cityId)
        }
    }
}
