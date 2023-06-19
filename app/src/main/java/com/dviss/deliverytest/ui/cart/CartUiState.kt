package com.dviss.deliverytest.ui.cart

import com.dviss.deliverytest.domain.model.CartItem
import com.dviss.deliverytest.domain.model.Dish
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class CartUiState(
    val location: String = "Unknown location",
    val cartUiItems: List<CartItemUiModel> = emptyList(),
) {
    private val currentDate = Calendar.getInstance().time
    private val dateFormat = SimpleDateFormat("d MMMM, yyyy", Locale.getDefault())
    val date: String = dateFormat.format(currentDate)
}

data class CartItemUiModel(
    val id: Int,
    val name: String,
    val price: Int,
    val weight: Int,
    val imageUrl: String,
    val count: Int
)

fun CartItemUiModel.toCartItem() = CartItem(
    id = id,
    number = count
)