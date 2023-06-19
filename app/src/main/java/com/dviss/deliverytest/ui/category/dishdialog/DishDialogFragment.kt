package com.dviss.deliverytest.ui.category.dishdialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.dviss.deliverytest.R
import com.dviss.deliverytest.domain.model.Dish
import com.dviss.deliverytest.ui.category.CategoryMenuViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DishDialogFragment(
    private val dish: Dish
) : DialogFragment() {

    private val categoryMenuViewModel: CategoryMenuViewModel by hiltNavGraphViewModels(R.id.mobile_navigation)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_dish, null)

        val builder = AlertDialog.Builder(requireActivity(), R.style.DishDialogStyle)
        builder.setView(dialogView)
        // Add dialog configuration if needed
        val dishImageView: ImageView = dialogView.findViewById(R.id.dialogDishImageView)
        val dishNameTextView: TextView = dialogView.findViewById(R.id.dialogDishNameTextView)
        val dishPriceTextView: TextView = dialogView.findViewById(R.id.dialogDishPriceTextView)
        val dishWeightTextView: TextView = dialogView.findViewById(R.id.dialogDishWeightTextView)
        val dishDescriptionTextView: TextView =
            dialogView.findViewById(R.id.dialogDishDescriptionTextView)
        val dishDialogConfirmButton: Button =
            dialogView.findViewById(R.id.dialogDishAddToCartButton)
        val dishDialogCloseIcon: ImageButton =
            dialogView.findViewById(R.id.dialogDishCloseImageButton)

        dishNameTextView.text = dish.name
        dishPriceTextView.text = dish.price.toInt().toString() + " ₽"
        dishWeightTextView.text = " · ${dish.weight.toInt()}г"
        dishDescriptionTextView.text = dish.description

        //load image
        Glide.with(dishImageView)
            .load(dish.imageUrl)
            .placeholder(R.color.dish_background) // Placeholder color
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .apply(RequestOptions().centerInside())
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(dishImageView)

        dishDialogConfirmButton.setOnClickListener {
            categoryMenuViewModel.addDishToCart(dish)
            dismiss()
        }

        dishDialogCloseIcon.setOnClickListener {
            dismiss()
        }

        return builder.create()
    }
}