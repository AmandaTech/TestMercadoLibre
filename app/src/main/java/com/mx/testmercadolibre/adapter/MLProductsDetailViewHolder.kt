package com.mx.testmercadolibre.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mx.testmercadolibre.R

class MLProductsDetailViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        val LAYOUT_ITEM = R.layout.list_picture
    }

   lateinit var imgThumbnail: ImageView



    private var mListener: ((item: MLProductsDetailModel) -> Unit)? = null
    private lateinit var mCurrent: MLProductsDetailModel


    fun bind(current: MLProductsDetailModel, listener: ((item: MLProductsDetailModel) -> Unit)?) {
        this.mCurrent = current
        this.mListener = listener
    }


    fun start() {


        imgThumbnail = itemView.findViewById(R.id.img_detail_product)


        insertaFoto()

        itemView.findViewById<View>(R.id.cl_search_product).setOnClickListener { view ->
            mListener?.invoke(mCurrent)

        }


    }


    fun insertaFoto() {
        Glide.with(itemView)
            .load(mCurrent.picture)
            .apply(RequestOptions())
            .override(200,150)
            .into(imgThumbnail)
    }
}