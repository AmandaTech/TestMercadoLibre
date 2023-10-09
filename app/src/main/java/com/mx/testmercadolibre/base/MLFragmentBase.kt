package com.mx.testmercadolibre.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class MLFragmentBase : Fragment() {

    var mListenerFragment: MLFragmentInteractorBase? = null
    lateinit var mView: View

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        this.mListenerFragment = activity as? MLFragmentInteractorBase
        if (this.mListenerFragment == null) {
            throw ClassCastException("$activity must implement OnArticleSelectedListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.mView = inflater.inflate(getLayoutId(), container, false)
        return this.mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        start()
        listener()

    }


    abstract fun getLayoutId(): Int

    abstract fun start()

    open fun listener() {

    }

    fun <T : View?> findViewById(id: Int): T = mView.findViewById<T>(id)


    fun onClick(id: Int, listener: View.OnClickListener) {
        mView.findViewById<View>(id).setOnClickListener(listener)
    }




}