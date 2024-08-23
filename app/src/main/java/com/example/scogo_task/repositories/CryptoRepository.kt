package com.example.scogo_task.repositories

import com.example.scogo_task.datasources.RetrofitInstance
import com.example.scogo_task.model.CryptoData

class CryptoRepository {
    suspend fun getCryptoData(): List<CryptoData> {
        return RetrofitInstance.api.getCryptoData()
    }
}