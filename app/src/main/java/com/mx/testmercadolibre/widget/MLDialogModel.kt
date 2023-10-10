package com.mx.testmercadolibre.widget
/**
 * Clase de modelo que representa los datos de un diálogo personalizado.
 *
 * @param titleMessage El título del mensaje del diálogo (puede ser nulo).
 * @param descMessage El mensaje descriptivo del diálogo (puede ser nulo).
 * @param primaryMessage El texto del botón de acción principal del diálogo (puede ser nulo).
 * @param primaryListener La función lambda que se ejecutará cuando se presione el botón de acción principal (puede ser nulo).
 */
class MLDialogModel private constructor(
    var titleMessage : String?,
    var descMessage : String?,
    val primaryMessage: String?,
    val primaryListener: () -> Unit?
) {
    /**
     * Clase de constructor de generador para construir instancias de [MLDialogModel].
     *
     * @param titleMessage El título del mensaje del diálogo (opcional).
     * @param descMessage El mensaje descriptivo del diálogo (opcional).
     * @param primaryMessage El texto del botón de acción principal del diálogo (opcional).
     * @param primaryListener La función lambda que se ejecutará cuando se presione el botón de acción principal (opcional).
     */
    data class Builder(
        var titleMessage: String? = null,
        var descMessage: String? = null,
        var primaryMessage: String? = null,
        var primaryListener: () -> Unit? = {}
    ) {
        fun titleMessage ( titleMessage: String?) = apply { this.titleMessage = titleMessage }
        fun descMessage ( descMessage: String?) = apply { this.descMessage = descMessage }
        fun primaryMessage( primaryMessage: String?) = apply { this.primaryMessage = primaryMessage }
        fun primaryListener(primaryListener: () -> Unit?) = apply { this.primaryListener = primaryListener }
        fun builder() =
            MLDialogModel(titleMessage , descMessage , primaryMessage, primaryListener)
    }
}
