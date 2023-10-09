package com.mx.testmercadolibre.expose

import androidx.fragment.app.Fragment
import com.mx.testmercadolibre.module.MLFragmentDetailProducts
import com.mx.testmercadolibre.module.MLFragmentProducts

data class MLNavigation(val navigationFragment: Fragment, val products: String) {

    enum class MLNavigationChoose(val stepId: Int ) {
        ML_LISTPRODUCTS(1),
        ML_DETAILPRODUCTS(2);

        fun isEqual ( stepId : Int ): Boolean {
            return getByStepId(stepId).stepId == this.stepId
        }

        companion object {
            fun getByStepId(stepId: Int?): MLNavigationChoose {
                for ( value in MLNavigationChoose.values() ) {
                    if ( value.stepId == stepId ) return value
                }
                return ML_LISTPRODUCTS

            }
        }

    }


    companion object {

        fun getFragmentByEnumChoose(select: MLNavigationChoose, products: String): Fragment {
            return when (select) {
                MLNavigationChoose.ML_LISTPRODUCTS -> {
                    MLFragmentProducts.createFragment(products)
                }
                MLNavigationChoose.ML_DETAILPRODUCTS -> {
                    MLFragmentDetailProducts.createFragment(products)
                }
                else -> {
                    MLFragmentProducts.createFragment(products)
                }
            }
        }
    }
}