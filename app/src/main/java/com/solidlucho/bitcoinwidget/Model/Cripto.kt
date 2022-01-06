package com.solidlucho.bitcoinwidget.Model

import com.google.gson.annotations.SerializedName
import java.lang.reflect.Type
import java.util.*

data class Cripto(

    var bitcoin: Map<String, Int>,

    //@SerializedName("usd")
    var usd: Double

)