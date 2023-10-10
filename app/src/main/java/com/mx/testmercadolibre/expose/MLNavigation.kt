package com.mx.testmercadolibre.expose

import androidx.fragment.app.Fragment
import com.mx.testmercadolibre.base.ShareDataFragment
import com.mx.testmercadolibre.module.MLFragmentDetailProducts
import com.mx.testmercadolibre.module.MLFragmentProducts
/**
 * Data class que representa una instancia de navegación en la aplicación.
 *
 * @property navigationFragment El fragmento de destino de la navegación.
 * @property products El identificador de los productos asociados a la navegación.
 * @property args Datos compartidos (opcional) para pasar entre fragmentos.
 */
data class MLNavigation(val navigationFragment: Fragment, val products: String,  val args: ShareDataFragment? = null) {
    /**
     * Enumeración que define los pasos posibles de navegación.
     *
     * @property stepId El identificador del paso de navegación.
     */
    enum class MLNavigationChoose(val stepId: Int ) {
        ML_LISTPRODUCTS(1),
        ML_DETAILPRODUCTS(2);
        /**
         * Comprueba si el paso de navegación actual es igual al paso proporcionado.
         *
         * @param stepId El identificador del paso a comparar.
         * @return `true` si son iguales, `false` en caso contrario.
         */
        fun isEqual ( stepId : Int ): Boolean {
            return getByStepId(stepId).stepId == this.stepId
        }

        companion object {
            /**
             * Obtiene un valor de `MLNavigationChoose` por su identificador de paso.
             *
             * @param stepId El identificador del paso de navegación.
             * @return El valor correspondiente de `MLNavigationChoose`.
             */
            fun getByStepId(stepId: Int?): MLNavigationChoose {
                for ( value in MLNavigationChoose.values() ) {
                    if ( value.stepId == stepId ) return value
                }
                return ML_LISTPRODUCTS

            }
        }

    }


    companion object {
        /**
         * Obtiene el fragmento de destino basado en la elección de navegación proporcionada.
         *
         * @param select La elección de navegación.
         * @param products El identificador de los productos asociados.
         * @return El fragmento correspondiente al paso de navegación.
         */

        fun getFragmentByEnumChoose(select: MLNavigationChoose, products: String ): Fragment {
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