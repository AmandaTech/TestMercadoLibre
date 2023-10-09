package com.mx.testmercadolibre.data.api

import com.google.gson.annotations.SerializedName

data class ResProducts(
    val results: List<ProductPreviewResponse>
)

data class ProductPreviewResponse(
    @SerializedName("accepts_mercadopago")
    val acceptsMercadopago: Boolean?,
    val id: String?,
    @SerializedName("seller_address")
    val sellerAddress: SellerAddress?,
    val price: Double?,
    val title: String?,
    val thumbnail: String?,
)
