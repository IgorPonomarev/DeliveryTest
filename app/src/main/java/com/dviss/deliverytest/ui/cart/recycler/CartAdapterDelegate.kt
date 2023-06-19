package com.dviss.deliverytest.ui.cart.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.dviss.deliverytest.R
import com.dviss.deliverytest.ui.cart.CartItemUiModel
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

class CartAdapterDelegate(
    private val onItemClickListener: CartAdapter.OnItemClickListener?
) : AdapterDelegate<List<Any>>() {

    override fun isForViewType(items: List<Any>, position: Int): Boolean {
        return items[position] is CartItemUiModel
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(
        items: List<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val viewHolder = holder as CartViewHolder
        val item = items[position] as CartItemUiModel
        viewHolder.bind(item)
    }

    class CartViewHolder(
        itemView: View,
        private val onItemClickListener: CartAdapter.OnItemClickListener?
    ) : RecyclerView.ViewHolder(itemView) {

            private val nameTextView: TextView = itemView.findViewById(R.id.cartDishNameTextView)
            private val priceTextView: TextView = itemView.findViewById(R.id.dialogDishPriceTextView)
            private val weightTextView: TextView = itemView.findViewById(R.id.dialogDishWeightTextView)
            private val counterTextView: TextView = itemView.findViewById(R.id.cartItemCounterTextView)
            private val imageView: ImageView = itemView.findViewById(R.id.cartImageView)
            private val minusImageView: ImageView = itemView.findViewById(R.id.cartItemCounterMinusImageView)
            private val plusImageView: ImageView = itemView.findViewById(R.id.cartItemCounterPlusImageView)

            fun bind(item: CartItemUiModel) {
                nameTextView.text = item.name
                priceTextView.text = item.price
                weightTextView.text = item.weight
                counterTextView.text = item.count.toString()

                //load image
                Glide.with(imageView)
                    .load(item.imageUrl)
                    .placeholder(androidx.appcompat.R.color.material_grey_300) // Placeholder color
                    .apply(RequestOptions().centerCrop())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView)

                // Set click listener on the item view
                minusImageView.setOnClickListener {
                    onItemClickListener?.onMinusClick(item)
                }

                plusImageView.setOnClickListener {
                    onItemClickListener?.onPlusClick(item)
                }
            }
    }
}