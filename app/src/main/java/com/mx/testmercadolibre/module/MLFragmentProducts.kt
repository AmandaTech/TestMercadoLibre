package com.mx.testmercadolibre.module


import android.os.Bundle
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mx.testmercadolibre.R
import com.mx.testmercadolibre.adapter.AdapterProducts
import com.mx.testmercadolibre.adapter.ProductsModel
import com.mx.testmercadolibre.base.MLFragmentBase
import com.mx.testmercadolibre.expose.MLNavigation
import com.mx.testmercadolibre.data.api.ResProducts
import com.mx.testmercadolibre.remote.MLRemoteDataSource
import com.mx.testmercadolibre.utils.MLResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MLFragmentProducts: MLFragmentBase() {

    override fun getLayoutId() = R.layout.fragment_products
    private lateinit var adapterProducts: AdapterProducts
    private lateinit var assignedProductsRecyclerView: RecyclerView
    private lateinit var productSearch: String
    private lateinit var tvInitSearch: TextView

    companion object {
        val CURRENT_STEP = MLNavigation.MLNavigationChoose.ML_LISTPRODUCTS.stepId

        fun createFragment(products: String) =
            MLFragmentProducts().apply {
                arguments = Bundle().apply {
                    productSearch = products
                }
            } as Fragment
    }

    override fun start() {
        tvInitSearch = findViewById(R.id.tv_init_search)
        validate()

    }
    private fun validate(){
        if (productSearch.isNotEmpty()){
            tvInitSearch.isVisible = false
            settingAdapter()
            searchLvl()
        }else{
            tvInitSearch.isVisible = true
        }
    }
    private fun settingAdapter() {
        assignedProductsRecyclerView = view?.findViewById(R.id.rv_items)!!
        adapterProducts = AdapterProducts { products ->
            mListenerFragment?.changeFragment(CURRENT_STEP)
        }
        assignedProductsRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        assignedProductsRecyclerView.adapter = adapterProducts
    }

    private fun searchLvl() {

        CoroutineScope(Dispatchers.IO).launch {
            val data = MLRemoteDataSource().searchItems(productSearch)
            CoroutineScope(Dispatchers.Main).launch {
                try {

                    if (mListenerFragment?.handlerMessageErrorApigee(data) == true) {
                        return@launch
                    }

                    if (data.data?.results?.isNotEmpty() == true) {
                        updateData(data)
                    } else {
//                        mListenerFragment?.showMessage(
//                            getString(R.string.title_message_one), "no se pude acutalizar"
//                        )
                    }
                } catch (e: Exception) {

                } finally {

                    //  mListenerFragment?.dialogWait(false)
                }
            }
        }


    }
    private fun updateData(response: MLResource<ResProducts>) {

        val result = response.data
        var listProducts = ArrayList<ProductsModel>()
        var ind = result?.results?.size
        var cont = 0
        while(cont < ind!!){
            listProducts.add(
                ProductsModel(result?.results?.get(cont)?.title,
                    result?.results?.get(cont)?.price,
                    result?.results?.get(cont)?.acceptsMercadopago,
                    result?.results?.get(cont)?.sellerAddress?.city?.name.toString(),
                    result?.results?.get(cont)?.thumbnail,
                )
            )
            cont++
           }
        adapterProducts.updateData(listProducts)


    }

}