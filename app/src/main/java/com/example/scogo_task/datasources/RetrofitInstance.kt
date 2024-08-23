package com.example.scogo_task.datasources

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.coinpaprika.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: CryptoAPI by lazy {
        retrofit.create(CryptoAPI::class.java)
    }
}
