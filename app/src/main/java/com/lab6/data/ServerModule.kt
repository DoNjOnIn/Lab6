package com.lab6.data

import com.lab6.data.entity.response.MoonPhaseResponse
import retrofit2.http.GET


interface ServerApi {
    @GET("b/J74S")
    suspend fun getMoonPhase(): MoonPhaseResponse
}
