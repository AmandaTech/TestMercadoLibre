package com.mx.testmercadolibre.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.mx.testmercadolibre.data.api.ResProducts
import com.mx.testmercadolibre.utils.MLResource

interface MLFragmentInteractorBase {
    fun inflater(fragment: Fragment)
    fun modifyDotScreen(int: Int, show: Boolean? = true)
    fun changeFragment(step: Int,productsId: String): Fragment
    fun changeFragmentFlow(step: Int,productsId: String): Fragment
    fun handlerMessageErrorApigee(MLResource : MLResource<Any>) : Boolean


}
