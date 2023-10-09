package com.mx.testmercadolibre.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdapterProducts(var listener: ((item: ProductsModel) -> Unit)?) : RecyclerView.Adapter<MLProductsViewHolder>() {

    private var items: List<ProductsModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MLProductsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(MLProductsViewHolder.LAYOUT_ITEM, parent, false)
        return MLProductsViewHolder(view)
    }

    override fun onBindViewHolder(holder: MLProductsViewHolder, position: Int) {
        try {
            val current = items.get(position)

            holder.bind(current , listener)
            holder.start()

        } catch (e: Exception) {

        }
    }

    fun updateData(items: List<ProductsModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }


}