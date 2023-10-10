package com.mx.testmercadolibre.adapter

import com.mx.testmercadolibre.data.api.SellerAddress

data class MLProductsModel(
    val id: String?,
    val title: String?,
    val price: Double?,
    val acceptsMercadopago: Boolean?,
    val sellerAddress: String?,
    val thumbnail: String?,

    )