package com.mx.testmercadolibre.module

import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mx.testmercadolibre.R
import com.mx.testmercadolibre.adapter.AdapterDetailProducts
import com.mx.testmercadolibre.adapter.ProductsDetailModel
import com.mx.testmercadolibre.adapter.ProductsModel
import com.mx.testmercadolibre.base.MLFragmentBase
import com.mx.testmercadolibre.data.api.ProductDetailsResponse
import com.mx.testmercadolibre.expose.MLNavigation
import com.mx.testmercadolibre.remote.MLRemoteDataSource
import com.mx.testmercadolibre.utils.MLResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MLFragmentDetailProducts: MLFragmentBase() {
    override fun getLayoutId() = R.layout.fragment_detail_products
    private lateinit var productId: String
    private lateinit var adapterDetailProducts: AdapterDetailProducts
    private lateinit var assignedDetailProductsRecyclerView: RecyclerView
    private lateinit var tvNameProductDetail: TextView
    private lateinit var tvDescriptProductDetail: TextView


    companion object {
        val CURRENT_STEP = MLNavigation.MLNavigationChoose.ML_DETAILPRODUCTS.stepId

        fun createFragment(products: String) =
            MLFragmentDetailProducts().apply {
                arguments = Bundle().apply {
                    productId = products
                }
            } as Fragment
    }
    override fun start() {
        tvNameProductDetail = findViewById(R.id.tv_name_product_detail)
        tvDescriptProductDetail = findViewById(R.id.tv_descript_product_detail)
        settingAdapter()
        detailProduct()
        detailDescriptionProduct()
    }
    private fun settingAdapter() {
        assignedDetailProductsRecyclerView = view?.findViewById(R.id.rv_productImage)!!
        adapterDetailProducts = AdapterDetailProducts { productsDetail ->
            //detailProduct()
        }
        assignedDetailProductsRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        assignedDetailProductsRecyclerView.adapter = adapterDetailProducts
    }
    private fun detailProduct() {

        CoroutineScope(Dispatchers.IO).launch {
            val data = MLRemoteDataSource().detailProduct(productId)
            CoroutineScope(Dispatchers.Main).launch {
                try {

                    if (mListenerFragment?.handlerMessageErrorApigee(data) == true) {
                        return@launch
                    }

                    if (data.status == MLResource.Status.SUCCESS) {
                        updateData(data.data)
                        tvNameProductDetail.text = data.data?.title
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

    private fun detailDescriptionProduct() {

        CoroutineScope(Dispatchers.IO).launch {
            val data = MLRemoteDataSource().detailDescriptionProduct(productId)
            CoroutineScope(Dispatchers.Main).launch {
                try {

                    if (mListenerFragment?.handlerMessageErrorApigee(data) == true) {
                        return@launch
                    }

                    if (data.status == MLResource.Status.SUCCESS) {
                        tvDescriptProductDetail.text = data.data?.text
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

    private fun updateData(response: ProductDetailsResponse?) {

        val result = response?.pictures
        var listProducts = ArrayList<ProductsDetailModel>()
        var ind = result?.size
        var cont = 0
        while(cont < ind!!){
            listProducts.add(
                ProductsDetailModel(result?.get(cont)?.secureUrl)

            )
            cont++
        }
        adapterDetailProducts.updateData(listProducts)


    }

}