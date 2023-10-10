package com.mx.testmercadolibre.widget

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.mx.testmercadolibre.R
/**
 * Clase que representa un diálogo personalizado en la aplicación.
 */
class MLDialogMessage : DialogFragment() {

    private lateinit var model: MLDialogModel
    private val DIALOG_LAYOUT : Int = R.layout.ml_dialog_message

    companion object {
        /**
         * Muestra un diálogo personalizado en una actividad.
         *
         * @param activity La actividad desde la que se muestra el diálogo.
         * @param model El modelo de diálogo que contiene los datos del diálogo.
         * @return Una instancia de [MLDialogMessage] que representa el diálogo creado y mostrado.
         */
        fun show(activity: FragmentActivity, model: MLDialogModel): MLDialogMessage {
            val fragmentManager = activity.supportFragmentManager
            val fragment = MLDialogMessage()
            fragment.putData(model)
            fragment.show(fragmentManager, MLDialogMessage::class.java.simpleName)
            return fragment
        }
        /**
         * Cierra un diálogo dado.
         *
         * @param dialog El diálogo que se va a cerrar.
         */
        fun dismiss(dialog: DialogFragment) {
            try {
                dialog.dismiss()
            } catch (e: Exception) {
                // Manejar cualquier excepción que pueda ocurrir al cerrar el diálogo
            }
        }
    }

    private fun putData(model: MLDialogModel) {
        this.model = model
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(DIALOG_LAYOUT, container)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Establecer fondo transparente y desactivar la capacidad de cerrar el diálogo tocando fuera
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCancelable(false)

        // Obtener referencias a las vistas del diseño del diálogo
        var btnPrimary = getView()?.findViewById<TextView>(R.id.btn_primary)
        var titleMessage = getView()?.findViewById<TextView>(R.id.tv_title)
        var descMessage = getView()?.findViewById<TextView>(R.id.tv_message)

        // Establecer el título del mensaje si está presente
        if (this.model.titleMessage != null) {
            titleMessage?.text = this.model.titleMessage
        } else {
            titleMessage?.visibility = View.GONE
        }

        // Establecer el mensaje descriptivo si está presente
        if (this.model.descMessage != null) {
            descMessage?.text = this.model.descMessage
        } else {
            descMessage?.visibility = View.GONE
        }
        // Establecer el texto y el oyente del botón principal si está presente
        if (this.model.primaryMessage != null) {
            btnPrimary?.text = this.model.primaryMessage
            btnPrimary?.setOnClickListener {
                this.model.primaryListener()
                dismiss()
            }
        } else {
            btnPrimary?.visibility = View.GONE
        }


    }


}