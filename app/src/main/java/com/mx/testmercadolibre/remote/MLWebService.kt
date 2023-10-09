package com.mx.testmercadolibre.remote

import com.mx.testmercadolibre.data.api.ResProducts
import com.mx.testmercadolibre.data.base.Constants.GET_PRODUCTS
import retrofit2.Response
import retrofit2.http.*

interface MLWebService {

    @GET(GET_PRODUCTS)
    suspend fun searchItems(
        @Query("q") productName: String): Response<ResProducts>

}
