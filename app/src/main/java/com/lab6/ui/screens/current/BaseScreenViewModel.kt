package com.lab6.ui.screens.current

import androidx.lifecycle.ViewModel
import com.lab6.data.ServerApi
import com.lab6.data.entity.response.EclipseInfo
import com.lab6.data.entity.response.MoonPhaseResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class BaseScreenViewModel(
    private val serverApi: ServerApi,
) : ViewModel() {
    private val _moonPhase = MutableStateFlow<MoonPhaseResponse?>(null)
    val moonPhase: StateFlow<MoonPhaseResponse?> = _moonPhase

    suspend fun fetchMoonPhase() {
        try {

            val response = serverApi.getMoonPhase()

            _moonPhase.value = response
        } catch (e: Exception) {
            _moonPhase.value = null
        }
    }

}





