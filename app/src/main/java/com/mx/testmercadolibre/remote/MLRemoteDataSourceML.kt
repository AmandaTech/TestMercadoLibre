package com.mx.testmercadolibre.remote


import com.mx.testmercadolibre.adapter.MLDescription
import com.mx.testmercadolibre.data.api.ProductDetailsResponse
import com.mx.testmercadolibre.data.api.ResProducts
import com.mx.testmercadolibre.data.base.MLBaseDataSource
import com.mx.testmercadolibre.data.base.MLConstants
import com.mx.testmercadolibre.utils.MLResource
/**
 * Clase que actúa como fuente de datos remota para interactuar con la API de MercadoLibre.
 */
class MLRemoteDataSourceML: MLBaseDataSource() {
    /**
     * Obtiene la URL base para las solicitudes a la API.
     *
     * @return La URL base de la API de MercadoLibre.
     */
    override fun getUrl() = MLConstants.BASE_URL
    /**
     * Obtiene la clase de servicio web Retrofit asociada a la API de MercadoLibre.
     *
     * @return La clase de servicio web Retrofit.
     */
    override fun getClazz() = MLWebService::class.java
    /**
     * Realiza una búsqueda de productos en la API de MercadoLibre.
     *
     * @param items Los términos de búsqueda de productos.
     * @return Un [MLResource] que representa el resultado de la búsqueda de productos.
     */
    suspend fun searchItems(items: String): MLResource<ResProducts> {
        return getResult { getRetrofit<MLWebService>().searchItems(items)  }
    }
    /**
     * Obtiene los detalles de un producto por su ID desde la API de MercadoLibre.
     *
     * @param items El ID del producto.
     * @return Un [MLResource] que representa los detalles del producto.
     */

    suspend fun detailProduct(items: String): MLResource<ProductDetailsResponse> {
        return getResult { getRetrofit<MLWebService>().detailProduct(items)  }
    }
    /**
     * Obtiene la descripción de un producto por su ID desde la API de MercadoLibre.
     *
     * @param items El ID del producto.
     * @return Un [MLResource] que representa la descripción del producto.
     */
    suspend fun detailDescriptionProduct(items: String): MLResource<MLDescription> {
        return getResult { getRetrofit<MLWebService>().getDescriptionById(items)  }
    }

}