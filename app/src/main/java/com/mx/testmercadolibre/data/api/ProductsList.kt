package com.mx.testmercadolibre.data.api

import com.google.gson.annotations.SerializedName
/**
 * Clase de datos que representa una respuesta de productos desde la API.
 *
 * @property results Una lista de productos previos.
 */
data class ResProducts(
    val results: List<ProductPreviewResponse>
)
/**
 * Clase de datos que representa un producto previo en la respuesta de la API.
 *
 * @property acceptsMercadopago Indica si el producto acepta MercadoPago.
 * @property id El identificador único del producto.
 * @property sellerAddress La dirección del vendedor.
 * @property price El precio del producto.
 * @property title El título o nombre del producto.
 * @property thumbnail La URL de la imagen en miniatura del producto.
 */
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
