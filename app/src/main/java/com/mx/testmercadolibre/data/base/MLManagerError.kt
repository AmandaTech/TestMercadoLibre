package com.mx.testmercadolibre.data.base

import com.google.gson.Gson
import retrofit2.Response

/**
 * Objeto singleton que proporciona funciones para gestionar errores de respuestas HTTP.
 */
object MLManagerError {
    /**
     * Obtiene el mensaje de error de una respuesta HTTP.
     *
     * @param response La respuesta HTTP que contiene el error.
     * @return El mensaje de error extraído de la respuesta.
     */
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

    /**
     * Modelo de datos que representa un error manejado por la aplicación.
     *
     * @property codigo El código de error.
     * @property mensaje El mensaje de error principal.
     * @property folio El número de folio asociado al error.
     * @property info Información adicional sobre el error.
     * @property detalles Una lista de detalles adicionales sobre el error.
     */
    data class ManagerError(
        val codigo: String,
        val mensaje: String,
        val folio: String,
        val info: String,
        val detalles: List<String>
    )
}


