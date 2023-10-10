package com.mx.testmercadolibre.expose

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.view.WindowManager
import com.mx.testmercadolibre.MLMainActivity
import com.mx.testmercadolibre.R
/**
 * Clase de utilidad para mostrar y cerrar un diálogo personalizado de progreso.
 */
class MLNavigationExposeUtils {

        companion object {

            private lateinit var isDialog: Dialog
            private val TAG: String = MLNavigationExposeUtils::class.java.simpleName

            /**
             * Abre un diálogo personalizado de progreso.
             *
             * @param activity La actividad desde la que se muestra el diálogo.
             */
            fun openCustomDialog(activity: Activity){
                val dialog = Dialog(activity, R.style.MLCustomBottomSheetDialog )
                val inflater = activity.layoutInflater
                val dialogView = inflater.inflate(R.layout.ly_progress,null)
                isDialog = dialog
                isDialog.setContentView(dialogView)
                isDialog.window!!.setLayout(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT)
                isDialog.window!!.setGravity(Gravity.CENTER_HORIZONTAL)
                isDialog.setCancelable(false)
                isDialog.show()

            }
            /**
             * Cierra el diálogo personalizado de progreso.
             */
            fun dismissCustomDialog(){
                isDialog.dismiss()
            }

            fun gotoMain( context : Context) {
                var intent: Intent = MLMainActivity.createIntent(context , MLMainActivity.MODE_MAIN)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }
        }
}
