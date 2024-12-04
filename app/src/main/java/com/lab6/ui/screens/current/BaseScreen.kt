package com.lab6.ui.screens.current

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel


@Composable
fun BaseScreen(
    viewModel: BaseScreenViewModel = getViewModel(),
    onCalendar: () -> Unit,
    onSolarEclipse: () -> Unit
) {
    val moonPhase by viewModel.moonPhase.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchMoonPhase()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            moonPhase?.let {
                Text(
                    text = "Date: ${it.datestamp}",
                    color = Color.White
                )
                Text(
                    text = "Moon Phase: ${it.moon.phaseName}",
                    color = Color.White
                )
                Text(
                    text = "Age: ${it.moon.ageDays} Days",
                    color = Color.White
                )


                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(RoundedCornerShape(12.dp))
                ) {
                    Text(
                        text = "${it.moon.emoji}",
                        fontSize = 100.sp,
                        color = Color.White
                    )
                }
            } ?: Text(
                text = "Loading Moon Phase...",
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        Button(
            onClick = onCalendar,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Text(text = "Calendar")
        }
        Button(
            onClick = onSolarEclipse,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Text(text = "Eclipse info")
        }
    }
}

