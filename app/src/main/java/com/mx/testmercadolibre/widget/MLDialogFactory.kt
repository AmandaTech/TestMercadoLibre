package com.mx.testmercadolibre.widget

import androidx.fragment.app.FragmentActivity

class MLDialogFactory {

    companion object {
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