package com.mx.testmercadolibre.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

abstract class MLBaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        start()
        listener()
    }

    abstract fun start()

    abstract fun listener()


    abstract fun getLayoutId() : Int

    fun onClick(idRes: Int , listener : (()->Unit)) {
        findViewById<View>(idRes).setOnClickListener {
            listener.invoke()
        }
    }


}