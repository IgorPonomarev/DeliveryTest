package com.dviss.deliverytest.ui.cart.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dviss.deliverytest.domain.model.Category
import com.dviss.deliverytest.ui.cart.CartItemUiModel
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager


class CartAdapter(
    onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: MutableList<Any> = mutableListOf()
    private val delegatesManager: AdapterDelegatesManager<List<Any>> = AdapterDelegatesManager()

    init {
        delegatesManager.addDelegate(CartAdapterDelegate(onItemClickListener))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegatesManager.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegatesManager.onBindViewHolder(items, position, holder, emptyList<Any>())
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return delegatesManager.getItemViewType(items, position)
    }

    fun setItems(newItems: List<Any>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onPlusClick(item: CartItemUiModel)
        fun onMinusClick(item: CartItemUiModel)
    }
}
