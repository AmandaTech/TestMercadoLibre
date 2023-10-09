package com.mx.testmercadolibre.base

import androidx.fragment.app.Fragment
import com.mx.testmercadolibre.utils.MLResource

interface MLFragmentInteractorBase {
    fun inflater(fragment: Fragment)
    fun modifyDotScreen(int: Int, show: Boolean? = true)
    fun changeFragment(step: Int,args: ShareDataFragment? = null): Fragment
    fun changeFragmentFlow(step: Int, args: ShareDataFragment? = null): Fragment
    fun handlerMessageErrorApigee(MLResource : MLResource<Any>) : Boolean

}
