package com.mx.testmercadolibre.widget

class MLDialogModel private constructor(
    var titleMessage : String?,
    var descMessage : String?,
    val primaryMessage: String?,
    val primaryListener: () -> Unit?
) {

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
