package com.mx.testmercadolibre.data.base

object Constants {
    const val BASE_URL = "https://api.mercadolibre.com/"
    private const val SITE_ID = "MLM"

    //ENDPOINTS
    const val GET_PRODUCTS = "sites/$SITE_ID/search"
    const val GET_DETAILS = "items/{productId}"
    const val GET_DESCRIPTION = "items/{productId}/description"

}
