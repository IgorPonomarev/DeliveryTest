package com.dviss.deliverytest.ui.category

import com.dviss.deliverytest.domain.model.Category
import com.dviss.deliverytest.domain.model.Dish
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class CategoryMenuUiState(
    val categoryTitle: String = "",
    val dishes: List<Dish> = emptyList()
)
