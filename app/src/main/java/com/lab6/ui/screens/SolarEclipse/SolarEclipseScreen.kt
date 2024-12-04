package com.lab6.ui.screens.SolarEclipse


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lab6.ui.screens.current.BaseScreenViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun SolarEclipseScreen(
    viewModel: BaseScreenViewModel = getViewModel(),
) {

    LaunchedEffect(Unit) {
        viewModel.fetchMoonPhase()
    }

    val moonPhase by viewModel.moonPhase.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            moonPhase?.let {
                Text(
                    text = "Next Solar Eclipse",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Date: ${it.nextSolarEclipse.datestamp}",
                    color = Color.White
                )
                Text(
                    text = "Type: ${it.nextSolarEclipse.type}",
                    color = Color.White
                )
                Text(
                    text = "Visibility Regions: ${it.nextSolarEclipse.visibilityRegions}",
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(64.dp))


                Text(
                    text = "Next Lunar Eclipse",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Date: ${it.nextLunarEclipse.datestamp}",
                    color = Color.White
                )
                Text(
                    text = "Type: ${it.nextLunarEclipse.type}",
                    color = Color.White
                )
                Text(
                    text = "Visibility Regions: ${it.nextLunarEclipse.visibilityRegions}",
                    color = Color.White
                )
             }
        }
    }
}
