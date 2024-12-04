package com.lab6.ui.screens.current

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lab6.data.ServerApi
import com.lab6.data.entity.response.WeatherForecastResponse
import com.lab6.data.entity.response.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherScreenViewModel(
    private val serverModule: ServerApi
) : ViewModel() {

    private val itemsList = listOf(
        Item(1, "Львів", 49.842957, 24.031111),
        Item(2, "Київ", 50.450001, 30.523333),
        Item(3, "Мадрид", 40.416775, -3.703790),
    )

    private val _weatherResponseStateFlow = MutableStateFlow<WeatherResponse?>(null)
    val weatherResponseStateFlow: StateFlow<WeatherResponse?>
        get() = _weatherResponseStateFlow

    private val _weatherForecastResponseStateFlow = MutableStateFlow<WeatherForecastResponse?>(null)
    val weatherForecastResponseStateFlow: StateFlow<WeatherForecastResponse?>
        get() = _weatherForecastResponseStateFlow

    private val _selectedTitle = MutableStateFlow<String?>(null)
    val selectedTitle: StateFlow<String?>
        get() = _selectedTitle



    fun fetchWeatherById(id: Int) {
        val item = itemsList.find { it.id == id } ?: return
        viewModelScope.launch {

            _selectedTitle.value = item.title

            _weatherResponseStateFlow.value = serverModule.getCurrentWeather(
                lat = item.lat,
                lon = item.lon
            )

            val weatherForecastResponse = serverModule.getWeatherForecast(
                lat = item.lat,
                lon = item.lon
            )
            Log.e("WeatherForecastScreenViewModel", "$weatherForecastResponse")
            _weatherForecastResponseStateFlow.value = weatherForecastResponse
        }
    }




    data class Item(val id: Int, val title: String, val lat: Double, val lon: Double)
    fun getItemsList(): List<Item> = itemsList
}

