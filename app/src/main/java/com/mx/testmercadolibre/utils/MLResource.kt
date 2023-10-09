package com.mx.testmercadolibre.utils

data class MLResource<out T> (val status : Status, val data : T?, val message : String? ) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success( data : T) : MLResource<T> {
            return MLResource(Status.SUCCESS , data , null)
        }

        fun <T> error( message : String , data : T? = null ) : MLResource<T> {
            return MLResource(Status.ERROR , data , message )
        }

        fun <T> loading ( data : T? = null ) : MLResource<T> {
            return MLResource(Status.LOADING , data , null)
        }
    }
}
