package com.mx.testmercadolibre.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
/**
 * Clase de adaptador para mostrar una lista de productos en un RecyclerView.
 *
 * @param listener La función lambda que se ejecutará cuando se haga clic en un elemento de la lista.
 */
class AdapterProducts(var listener: ((item: MLProductsModel) -> Unit)?) : RecyclerView.Adapter<MLProductsViewHolder>() {

     // Lista de elementos (productos) que se mostrarán en el RecyclerView.
    private var items: List<MLProductsModel> = arrayListOf()
    /**
     * Crea y devuelve una instancia de [MLProductsViewHolder] para un elemento de la lista.
     *
     * @param parent El ViewGroup padre en el que se inflará la vista del elemento.
     * @param viewType El tipo de vista del elemento (no se utiliza en este caso).
     * @return Una instancia de [MLProductsViewHolder] que representa un elemento de la lista.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MLProductsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(MLProductsViewHolder.LAYOUT_ITEM, parent, false)
        return MLProductsViewHolder(view)
    }
    /**
     * Vincula los datos de un elemento de la lista a una instancia de [MLProductsViewHolder].
     *
     * @param holder La instancia de [MLProductsViewHolder] en la que se mostrarán los datos.
     * @param position La posición del elemento en la lista.
     */
    override fun onBindViewHolder(holder: MLProductsViewHolder, position: Int) {
        try {
            val current = items.get(position)

            holder.bind(current , listener)
            holder.start()

        } catch (e: Exception) {

        }
    }
    /**
     * Actualiza la lista de elementos (productos) que se mostrarán en el RecyclerView.
     *
     * @param items La nueva lista de elementos (productos) a mostrar.
     */
    fun updateData(items: List<MLProductsModel>) {
        this.items = items
        notifyDataSetChanged()
    }
    /**
     * Obtiene la cantidad de elementos en la lista.
     *
     * @return El número de elementos en la lista.
     */
    override fun getItemCount(): Int {
        return items?.size ?: 0
    }


}