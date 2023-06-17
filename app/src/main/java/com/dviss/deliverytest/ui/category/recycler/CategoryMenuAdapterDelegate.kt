package com.dviss.deliverytest.ui.category.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.dviss.deliverytest.R
import com.dviss.deliverytest.domain.model.Dish
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.bumptech.glide.request.target.Target

class CategoryMenuAdapterDelegate(
    private val onItemClickListener: CategoryMenuAdapter.OnItemClickListener?
) : AdapterDelegate<List<Any>>() {

    override fun isForViewType(items: List<Any>, position: Int): Boolean {
        return items[position] is Dish
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dish, parent, false)
        return CategoryMenuViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(
        items: List<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val viewHolder = holder as CategoryMenuViewHolder
        val item = items[position] as Dish
        viewHolder.bind(item)
    }
}

class CategoryMenuViewHolder(
    itemView: View,
    private val onItemClickListener: CategoryMenuAdapter.OnItemClickListener?
) : RecyclerView.ViewHolder(itemView) {
    private val nameTextView: TextView = itemView.findViewById(R.id.dishNameTextView)
    private val imageView: ImageView = itemView.findViewById(R.id.dishImageView)

    fun bind(dish: Dish) {
        nameTextView.text = dish.name

        //load image
        Glide.with(itemView)
            .load(dish.imageUrl)
            .placeholder(R.color.dish_background) // Placeholder color
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .apply(RequestOptions().centerInside())
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)

        itemView.setOnClickListener {
            onItemClickListener?.onDishClick(dish)
        }
    }
}