package com.mx.testmercadolibre.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mx.testmercadolibre.R


class MLProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        val LAYOUT_ITEM = R.layout.list_products
    }

    lateinit var mtvNameProduct: TextView
    lateinit var mtvPrice: TextView
    lateinit var mtvSeller: TextView
    lateinit var imgThumbnail: ImageView



    private var mListener: ((item: ProductsModel) -> Unit)? = null
    private lateinit var mCurrent: ProductsModel


    fun bind(current: ProductsModel, listener: ((item: ProductsModel) -> Unit)?) {
        this.mCurrent = current
        this.mListener = listener
    }


    fun start() {

        mtvNameProduct = itemView.findViewById(R.id.tv_name_items)
        mtvPrice = itemView.findViewById(R.id.tv_price)
        mtvSeller = itemView.findViewById(R.id.tv_seller)
        imgThumbnail = itemView.findViewById(R.id.img_items)

        mtvNameProduct.text = mCurrent.title?.substring(0, 30) + "..."
        mtvPrice.text = "$ " + mCurrent.price.toString()
        mtvSeller.text = mCurrent.sellerAddress.toString()

        insertaFoto()

        itemView.findViewById<View>(R.id.cl_search_product).setOnClickListener { view ->
            mListener?.invoke(mCurrent)

        }


    }


    fun insertaFoto() {
        Glide.with(itemView)
            .load(mCurrent.thumbnail)
            .apply(RequestOptions())
            .override(60,60)
            .into(imgThumbnail)
    }
}