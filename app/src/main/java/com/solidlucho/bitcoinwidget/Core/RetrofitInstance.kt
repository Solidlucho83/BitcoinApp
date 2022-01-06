package com.solidlucho.bitcoinwidget.Core

import com.solidlucho.bitcoinwidget.Repository.DameValor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitInstance {
    var okHttpClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
                .baseUrl("https://api.coingecko.com/api/v3/simple/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    val api: DameValor by lazy {
        retrofit.create(DameValor::class.java)

    }
}