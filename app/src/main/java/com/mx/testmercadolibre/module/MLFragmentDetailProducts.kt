package com.mx.testmercadolibre.module

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mx.testmercadolibre.R
import com.mx.testmercadolibre.base.MLFragmentBase
import com.mx.testmercadolibre.expose.MLNavigation

class MLFragmentDetailProducts: MLFragmentBase() {
    override fun getLayoutId() = R.layout.fragment_detail_products
    companion object {
        val CURRENT_STEP = MLNavigation.MLNavigationChoose.ML_DETAILPRODUCTS.stepId

        fun createFragment(products: String) =
            MLFragmentDetailProducts().apply {
                arguments = Bundle().apply {
                    //productSearch = products
                }
            } as Fragment
    }
    override fun start() {

    }
}