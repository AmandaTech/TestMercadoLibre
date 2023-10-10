package com.mx.testmercadolibre.data.base
/**
 * Objeto singleton que almacena constantes utilizadas en la aplicación.
 */
object MLConstants {
    /**
     * URL base para las solicitudes a la API de MercadoLibre.
     */
    const val BASE_URL = "https://api.mercadolibre.com/"
    /**
     * Identificador del sitio de MercadoLibre.
     */
    private const val SITE_ID = "MLM"

    /**
     * Endpoint para obtener productos en el sitio especificado.
     */
    const val GET_PRODUCTS = "sites/$SITE_ID/search"
    const val GET_DETAILS = "items/{productId}"
    const val GET_DESCRIPTION = "items/{productId}/description"

}
