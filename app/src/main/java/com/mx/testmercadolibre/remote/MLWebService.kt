package com.mx.testmercadolibre.remote

import com.mx.testmercadolibre.adapter.Description
import com.mx.testmercadolibre.data.api.ProductDetailsResponse
import com.mx.testmercadolibre.data.api.ResProducts
import com.mx.testmercadolibre.data.base.Constants.GET_DESCRIPTION
import com.mx.testmercadolibre.data.base.Constants.GET_DETAILS
import com.mx.testmercadolibre.data.base.Constants.GET_PRODUCTS
import retrofit2.Response
import retrofit2.http.*

interface MLWebService {

    @GET(GET_PRODUCTS)
    suspend fun searchItems(
        @Query("q") productName: String): Response<ResProducts>

    @GET(GET_DETAILS)
    suspend fun detailProduct(
        @Path("productId") productId: String): Response<ProductDetailsResponse>

    @GET(GET_DESCRIPTION)
    suspend fun getDescriptionById(
        @Path("productId") productId: String
    ): Response<Description>
}
