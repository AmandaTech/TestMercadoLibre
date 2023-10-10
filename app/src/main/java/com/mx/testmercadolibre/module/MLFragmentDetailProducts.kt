package com.mx.testmercadolibre.module

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mx.testmercadolibre.R
import com.mx.testmercadolibre.adapter.MLAdapterDetailProducts
import com.mx.testmercadolibre.adapter.MLProductsDetailModel
import com.mx.testmercadolibre.base.MLFragmentBase
import com.mx.testmercadolibre.data.api.ProductDetailsResponse
import com.mx.testmercadolibre.expose.MLNavigation
import com.mx.testmercadolibre.remote.MLRemoteDataSourceML
import com.mx.testmercadolibre.utils.MLResource
import com.mx.testmercadolibre.widget.MLDialogFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MLFragmentDetailProducts: MLFragmentBase() {
    override fun getLayoutId() = R.layout.fragment_detail_products
    private lateinit var productId: String
    private lateinit var MLAdapterDetailProducts: MLAdapterDetailProducts
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
        // Ocultar la barra de herramientas
        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar?.hide()

        settingAdapter()
        detailProduct()
        detailDescriptionProduct()
    }
    private fun settingAdapter() {
        assignedDetailProductsRecyclerView = view?.findViewById(R.id.rv_productImage)!!
        MLAdapterDetailProducts = MLAdapterDetailProducts { productsDetail ->
        }
        assignedDetailProductsRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        assignedDetailProductsRecyclerView.adapter = MLAdapterDetailProducts
    }
    private fun detailProduct() {

        CoroutineScope(Dispatchers.IO).launch {
            val data = MLRemoteDataSourceML().detailProduct(productId)
            CoroutineScope(Dispatchers.Main).launch {
                try {

                    if (mListenerFragment?.handlerMessageErrorApigee(data) == true) {
                        return@launch
                    }

                    if (data.status == MLResource.Status.SUCCESS) {
                        updateData(data.data)
                        tvNameProductDetail.text = data.data?.title
                    } else {
                        openDialog(data.message.toString())

                    }
                } catch (e: Exception) {

                } finally {

                }
            }
        }


    }

    private fun detailDescriptionProduct() {

        CoroutineScope(Dispatchers.IO).launch {
            val data = MLRemoteDataSourceML().detailDescriptionProduct(productId)
            CoroutineScope(Dispatchers.Main).launch {
                try {

                    if (mListenerFragment?.handlerMessageErrorApigee(data) == true) {
                        return@launch
                    }

                    if (data.status == MLResource.Status.SUCCESS) {
                        tvDescriptProductDetail.text = data.data?.text
                    } else {
                        openDialog(data.message.toString())

                    }
                } catch (e: Exception) {

                } finally {

                }
            }
        }


    }
    fun openDialog(txt1:String){
        val txt2 = getString(R.string.st_accept)
        MLDialogFactory.createDesPrimaryButton(requireActivity(), txt1, txt2) {
            Log.d(ContentValues.TAG, "listener: ")
        }

    }

    private fun updateData(response: ProductDetailsResponse?) {

        val result = response?.pictures
        var listProducts = ArrayList<MLProductsDetailModel>()
        var ind = result?.size
        var cont = 0
        while(cont < ind!!){
            listProducts.add(
                MLProductsDetailModel(result?.get(cont)?.secureUrl)

            )
            cont++
        }
        MLAdapterDetailProducts.updateData(listProducts)


    }


}