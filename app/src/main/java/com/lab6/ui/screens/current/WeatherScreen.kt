package com.lab6.ui.screens.current

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lab6.ui.components.WeatherMainCustomView
import org.koin.androidx.compose.getViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


@SuppressLint("RememberReturnType")
@Composable
fun WeatherScreen(
    id: Int,
    viewModel: WeatherScreenViewModel = getViewModel()
) {
    remember(id) {
        viewModel.fetchWeatherById(id)
    }

    val weatherResponseState = viewModel.weatherResponseStateFlow.collectAsState()
    val weatherForecastResponseState = viewModel.weatherForecastResponseStateFlow.collectAsState()
    val selectedTitle = viewModel.selectedTitle.collectAsState()

    // State to hold the selected date for filtering
    val selectedDate = remember { mutableStateOf<Date?>(null) }

    // State to control when to show the date picker dialog
    val showDatePickerDialogState = remember { mutableStateOf(false) }

    // State for filtering forecast based on selected date
    val filteredForecast = remember(selectedDate.value) {
        weatherForecastResponseState.value?.list?.filter {
            val forecastDate = Date(it.dt * 1000)
            selectedDate.value?.let { selected ->
                // Compare only the date part (ignoring time)
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(forecastDate) == SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(selected)
            } ?: true
        }
    }

    // Show DatePickerDialog using a side-effect when required
    if (showDatePickerDialogState.value) {
        showDatePickerDialog(selectedDate)
        showDatePickerDialogState.value = false // Reset the state after showing
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = selectedTitle.value.toString(),
            fontSize = 22.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = weatherResponseState.value?.let {
                "Coordinates: lat=${it.coord.lat}, lon=${it.coord.lon}"
            } ?: "Fetching coordinates...",
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        weatherResponseState.value?.main?.let { weatherMain ->
            WeatherMainCustomView(weatherMain = weatherMain)
        }

        Button(
            onClick = {
                showDatePickerDialogState.value = true
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Select Date to Filter")
        }

        selectedDate.value?.let {
            Text(
                text = "Selected Date: ${SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(it)}",
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        filteredForecast?.let { forecastList ->
            LazyColumn {
                items(forecastList) { weatherForecast ->
                    weatherForecast.main.let { weatherMain ->
                        Text(
                            "Time: ${
                                SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(weatherForecast.dt * 1000))
                            }",
                            fontSize = 18.sp,
                            modifier = Modifier.fillMaxWidth()
                        )
                        WeatherMainCustomView(weatherMain = weatherMain)
                    }
                }
            }
        }
    }
}

@Composable
fun showDatePickerDialog(selectedDate: MutableState<Date?>) {
    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val selectedCal = Calendar.getInstance()
            selectedCal.set(year, month, dayOfMonth)
            selectedDate.value = selectedCal.time
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )
    datePickerDialog.show()
}




