package com.mx.testmercadolibre.utils
/**
 * Clase de datos que representa un recurso con estado (success, error o loading) que contiene datos opcionales y un mensaje.
 *
 * @param status El estado del recurso (SUCCESS, ERROR o LOADING).
 * @param data Los datos asociados al recurso (puede ser nulo).
 * @param message Un mensaje descriptivo del recurso (puede ser nulo en el caso de SUCCESS).
 */
data class MLResource<out T> (val status : Status, val data : T?, val message : String? ) {
    /**
     * Enumeración que define los estados posibles del recurso.
     */
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {

        /**
         * Crea un recurso de estado SUCCESS con los datos proporcionados.
         *
         * @param data Los datos asociados al recurso.
         * @return Un recurso de estado SUCCESS.
         */
        fun <T> success( data : T) : MLResource<T> {
            return MLResource(Status.SUCCESS , data , null)
        }
        /**
         * Crea un recurso de estado ERROR con el mensaje y datos proporcionados (opcional).
         *
         * @param message El mensaje descriptivo del error.
         * @param data Los datos asociados al error (puede ser nulo).
         * @return Un recurso de estado ERROR.
         */
        fun <T> error( message : String , data : T? = null ) : MLResource<T> {
            return MLResource(Status.ERROR , data , message )
        }
        /**
         * Crea un recurso de estado LOADING con los datos proporcionados (opcional).
         *
         * @param data Los datos asociados al recurso de loading (puede ser nulo).
         * @return Un recurso de estado LOADING.
         */
        fun <T> loading ( data : T? = null ) : MLResource<T> {
            return MLResource(Status.LOADING , data , null)
        }
    }
}
