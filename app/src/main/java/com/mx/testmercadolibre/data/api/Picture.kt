package com.mx.testmercadolibre.data.api

import com.google.gson.annotations.SerializedName

data class Picture(
    @SerializedName("secure_url")
    val secureUrl: String?,
)
