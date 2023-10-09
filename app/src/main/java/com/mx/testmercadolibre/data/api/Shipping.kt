package com.mx.testmercadolibre.data.api

import com.google.gson.annotations.SerializedName

data class Shipping(
    @SerializedName("free_shipping")
    val freeShipping: Boolean?,
)
