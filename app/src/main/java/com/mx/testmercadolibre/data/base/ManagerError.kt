package com.mx.testmercadolibre.data.base

import com.google.gson.Gson
import retrofit2.Response

object ManagerError {

    fun <T> getMessageError(response: Response<T>): String {
        return try {
            val data = Gson().fromJson(response.errorBody()?.string(), ManagerError::class.java)
            if (data.detalles.isNotEmpty()) {
                data.detalles[0]
            } else {
                data.mensaje
            }
        } catch (e: Exception) {
            ""
        }
    }

    data class ManagerError(
        val codigo: String,
        val mensaje: String,
        val folio: String,
        val info: String,
        val detalles: List<String>
    )
}


