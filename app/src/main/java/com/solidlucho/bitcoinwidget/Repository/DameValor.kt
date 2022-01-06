package com.solidlucho.bitcoinwidget.Repository

import com.solidlucho.bitcoinwidget.Model.Cripto
import retrofit2.http.GET

interface DameValor {

    @GET("price?ids=bitcoin&vs_currencies=usd")
    suspend fun getUsd(): Cripto


}