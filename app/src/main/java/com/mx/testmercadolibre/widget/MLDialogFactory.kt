package com.mx.testmercadolibre.widget

import androidx.fragment.app.FragmentActivity
/**
 * Clase de fábrica para crear diálogos personalizados en la aplicación.
 */
class MLDialogFactory {

    companion object {
        /**
         * Crea un diálogo con un botón de acción principal personalizado.
         *
         * @param fragmentActivity La actividad del fragmento que mostrará el diálogo.
         * @param desc El mensaje de descripción del diálogo.
         * @param btnPrimaryText El texto del botón de acción principal.
         * @param listener La función lambda que se ejecutará cuando se presione el botón principal.
         * @return Un objeto [MLDialogMessage] que representa el diálogo creado.
         */
        fun createDesPrimaryButton(
            fragmentActivity: FragmentActivity,
            desc: String,
            btnPrimaryText: String,
            listener: () -> Unit
        ): MLDialogMessage {
            val model: MLDialogModel = MLDialogModel.Builder()
                .descMessage(desc)
                .primaryMessage(btnPrimaryText)
                .primaryListener(listener)
                .builder()
            return MLDialogMessage.show(fragmentActivity, model)
        }

    }

}