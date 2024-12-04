package com.lab6.ui.screens.calendar


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lab6.ui.screens.current.BaseScreenViewModel
import org.koin.androidx.compose.getViewModel



@Composable
fun CalendarScreen(
    viewModel: BaseScreenViewModel = getViewModel(),
) {
    val moonPhaseResponse by viewModel.moonPhase.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchMoonPhase()
    }

    moonPhaseResponse?.let { moonPhaseData ->
        val months = listOf(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        )


        val moonPhasesMap = mapOf(
            "January" to moonPhaseData.moonPhases.january,
            "February" to moonPhaseData.moonPhases.february,
            "March" to moonPhaseData.moonPhases.march,
            "April" to moonPhaseData.moonPhases.april,
            "May" to moonPhaseData.moonPhases.may,
            "June" to moonPhaseData.moonPhases.june,
            "July" to moonPhaseData.moonPhases.july,
            "August" to moonPhaseData.moonPhases.august,
            "September" to moonPhaseData.moonPhases.september,
            "October" to moonPhaseData.moonPhases.october,
            "November" to moonPhaseData.moonPhases.november,
            "December" to moonPhaseData.moonPhases.december
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
                .padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(months) { index, month ->
                    Text(
                        text = month,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    val moonPhases = moonPhasesMap[month] ?: emptyList()

                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        itemsIndexed(moonPhases) { index, emoji ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = (index + 1).toString(),
                                    color = Color.LightGray,
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(bottom = 4.dp)
                                )

                                Text(
                                    text = emoji,
                                    fontSize = 30.sp,
                                    color = Color.Yellow
                                )
                            }
                        }
                    }
                }
            }
        }
    } ?: Text(
        text = "Loading Moon Phases...",
        color = Color.White,
        modifier = Modifier.fillMaxSize(),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}







