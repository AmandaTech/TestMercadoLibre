package com.mx.testmercadolibre.expose

import android.app.Activity
import android.app.Dialog
import android.view.Gravity
import android.view.WindowManager
import com.mx.testmercadolibre.R

class MLNavigationExposeUtils {

        companion object {

            private lateinit var isDialog: Dialog
            private val TAG: String = MLNavigationExposeUtils::class.java.simpleName



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

            fun dismissCustomDialog(){
                isDialog.dismiss()
            }
        }
}
