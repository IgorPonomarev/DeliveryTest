package com.dviss.deliverytest.ui.home.recycler

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
import com.dviss.deliverytest.domain.model.Category
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate

class CategoryAdapterDelegate(
    private val onItemClickListener: CategoryAdapter.OnItemClickListener?
) : AdapterDelegate<List<Any>>() {

    override fun isForViewType(items: List<Any>, position: Int): Boolean {
        return items[position] is Category
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(
        items: List<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val viewHolder = holder as CategoryViewHolder
        val item = items[position] as Category
        viewHolder.bind(item)
    }

    class CategoryViewHolder(
        itemView: View,
        private val onItemClickListener: CategoryAdapter.OnItemClickListener?
    ) : RecyclerView.ViewHolder(itemView) {

        private val nameTextView: TextView = itemView.findViewById(R.id.categoryName)
        private val imageView: ImageView = itemView.findViewById(R.id.categoryImage)

        fun bind(category: Category) {
            nameTextView.text = category.name

            //load image
            Glide.with(itemView)
                .load(category.imageUrl)
                .placeholder(androidx.appcompat.R.color.material_grey_300) // Placeholder color
                .apply(RequestOptions().centerCrop())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)

            // Set click listener on the item view
            itemView.setOnClickListener {
                onItemClickListener?.onCategoryItemClick(category)
            }
        }
    }
}