package com.mx.testmercadolibre.adapter

import com.google.gson.annotations.SerializedName


data class MLDescription(
    @SerializedName("plain_text")
    val text: String?
)