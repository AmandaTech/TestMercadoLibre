package com.mx.testmercadolibre.remote


import com.mx.testmercadolibre.adapter.Description
import com.mx.testmercadolibre.data.api.ProductDetailsResponse
import com.mx.testmercadolibre.data.api.ResProducts
import com.mx.testmercadolibre.data.base.BaseDataSource
import com.mx.testmercadolibre.data.base.Constants
import com.mx.testmercadolibre.utils.MLResource


class MLRemoteDataSource: BaseDataSource() {
    override fun getUrl() = Constants.BASE_URL

    override fun getClazz() = MLWebService::class.java

    suspend fun searchItems(items: String): MLResource<ResProducts> {
        return getResult { getRetrofit<MLWebService>().searchItems(items)  }
    }

    suspend fun detailProduct(items: String): MLResource<ProductDetailsResponse> {
        return getResult { getRetrofit<MLWebService>().detailProduct(items)  }
    }
    suspend fun detailDescriptionProduct(items: String): MLResource<Description> {
        return getResult { getRetrofit<MLWebService>().getDescriptionById(items)  }
    }

}