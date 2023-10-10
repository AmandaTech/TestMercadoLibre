package com.mx.testmercadolibre.remote

import com.mx.testmercadolibre.adapter.MLDescription
import com.mx.testmercadolibre.data.api.ProductDetailsResponse
import com.mx.testmercadolibre.data.api.ResProducts
import com.mx.testmercadolibre.data.base.MLConstants.GET_DESCRIPTION
import com.mx.testmercadolibre.data.base.MLConstants.GET_DETAILS
import com.mx.testmercadolibre.data.base.MLConstants.GET_PRODUCTS
import retrofit2.Response
import retrofit2.http.*
/**
 * Interfaz que define los métodos para realizar solicitudes a la API de MercadoLibre.
 */
interface MLWebService {
    /**
     * Realiza una solicitud para buscar productos en la API de MercadoLibre.
     *
     * @param productName El nombre o término de búsqueda de productos.
     * @return Una respuesta [Response] que contiene los resultados de la búsqueda de productos.
     */
    @GET(GET_PRODUCTS)
    suspend fun searchItems(
        @Query("q") productName: String): Response<ResProducts>
    /**
     * Realiza una solicitud para obtener los detalles de un producto por su ID desde la API de MercadoLibre.
     *
     * @param productId El ID único del producto.
     * @return Una respuesta [Response] que contiene los detalles del producto.
     */
    @GET(GET_DETAILS)
    suspend fun detailProduct(
        @Path("productId") productId: String): Response<ProductDetailsResponse>
    /**
     * Realiza una solicitud para obtener la descripción de un producto por su ID desde la API de MercadoLibre.
     *
     * @param productId El ID del producto.
     * @return Una respuesta [Response] que contiene la descripción del producto.
     */
    @GET(GET_DESCRIPTION)
    suspend fun getDescriptionById(
        @Path("productId") productId: String
    ): Response<MLDescription>
}
