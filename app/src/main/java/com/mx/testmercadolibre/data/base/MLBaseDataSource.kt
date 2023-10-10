package com.mx.testmercadolibre.data.base

import com.mx.testmercadolibre.utils.MLResource
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * Clase base abstracta que proporciona funcionalidad común para las fuentes de datos.
 */

abstract class MLBaseDataSource() {
    /**
     * Encabezados HTTP personalizados para las solicitudes.
     */
    private var headers: HashMap<String, String>? = null
    /**
     * Obtiene un cliente OkHttpClient personalizado que confía en todos los certificados SSL.
     *
     * @return Cliente OkHttpClient personalizado.
     */
    private fun getOkHttpClient(): OkHttpClient? {
        return try {
            val trustAllCerts = arrayOf<TrustManager>(
                object : X509TrustManager {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(
                        chain: Array<X509Certificate?>?,
                        authType: String?
                    ) {}
                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(
                        chain: Array<X509Certificate?>?,
                        authType: String?
                    ) {}
                    override fun getAcceptedIssuers(): Array<X509Certificate?>? { return arrayOf() }
                }
            )

            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            val sslSocketFactory = sslContext.socketFactory
            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { _, _ -> true }
            builder.addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                    val request: Request = chain.request()
                    val newRequest = request.newBuilder()
                    headers.let {
                        headers?.keys?.forEach {
                            newRequest.addHeader(it, headers!![it].toString())
                        }
                        return chain.proceed(newRequest.build())
                    }
                }
            })
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            builder.build()
        } catch (e: java.lang.Exception) {
            throw RuntimeException(e)
        }
    }

    /**
     * Realiza una llamada a la API y devuelve un recurso de datos envuelto en [MLResource].
     *
     * @param call Una función suspendida que realiza la llamada a la API.
     * @return Un [MLResource] que representa el resultado de la llamada a la API.
     */
    suspend fun <T> getResult(call: suspend () -> Response<T>): MLResource<T> {
        try {


            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return MLResource.success(body)
            }

            val messageError =
                MLManagerError.getMessageError(response)

            return if (messageError.isNotEmpty()) {
                error(messageError)
            } else {
                error("${response.body()}")

            }
        } catch (e: Exception) {
            return error("${e.message}")
        }
    }

    /**
     * Función privada que crea un recurso de error [MLResource].
     *
     * @param message El mensaje de error.
     * @return Un [MLResource] de error.
     */

    private fun <T> error(message: String): MLResource<T> {
        return MLResource.error(message)
    }

    abstract fun getUrl(): String
    abstract fun getClazz(): Class<*>

    fun <T> getRetrofit(): T {

        val retrofit = Retrofit.Builder()
            .baseUrl(getUrl())
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(getClazz()) as T
    }


}