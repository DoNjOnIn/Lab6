package com.lab6.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lab6.ui.screens.SolarEclipse.SolarEclipseScreen
import com.lab6.ui.screens.calendar.CalendarScreen
import com.lab6.ui.screens.current.BaseScreen
import com.lab6.ui.screens.current.BaseScreenViewModel
import org.koin.androidx.compose.getViewModel


const val SCREEN_BASE_SCREEN = "baseScreen"
const val SCREEN_CALENDAR_SCREEN = "calendarScreen"
const val SCREEN_SOLAR_ECLIPSE_SCREEN = "solarEclipseScreen"

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: BaseScreenViewModel = getViewModel()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = SCREEN_BASE_SCREEN
    ) {
        composable(
            route = SCREEN_BASE_SCREEN
        ) {
            BaseScreen(
                onCalendar = { navController.navigate(SCREEN_CALENDAR_SCREEN) },
                onSolarEclipse = { navController.navigate(SCREEN_SOLAR_ECLIPSE_SCREEN) }
            )
        }
        composable(
            route = SCREEN_CALENDAR_SCREEN
        ) {
            CalendarScreen()
        }
        composable(
            route = SCREEN_SOLAR_ECLIPSE_SCREEN
        ) {
            SolarEclipseScreen()
        }
    }
}

