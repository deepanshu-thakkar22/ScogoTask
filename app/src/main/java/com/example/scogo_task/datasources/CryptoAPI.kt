package com.example.scogo_task.datasources

import com.example.scogo_task.model.CryptoData
import retrofit2.http.GET

interface CryptoAPI {

    @GET("v1/coins")
    suspend fun getCryptoData(): List<CryptoData>
}