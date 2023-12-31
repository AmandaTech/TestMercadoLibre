package com.mx.testmercadolibre.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
class MLAdapterDetailProducts(var listener: ((item: MLProductsDetailModel) -> Unit)?) : RecyclerView.Adapter<MLProductsDetailViewHolder>() {

    private var items: List<MLProductsDetailModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MLProductsDetailViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(MLProductsDetailViewHolder.LAYOUT_ITEM, parent, false)
        return MLProductsDetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: MLProductsDetailViewHolder, position: Int) {
        try {
            val current = items.get(position)

            holder.bind(current , listener)
            holder.start()

        } catch (e: Exception) {

        }
    }

    fun updateData(items: List<MLProductsDetailModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }


}