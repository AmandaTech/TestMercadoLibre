package com.mx.testmercadolibre.adapter

import com.google.gson.annotations.SerializedName


data class Description(
    @SerializedName("plain_text")
    val text: String?
)