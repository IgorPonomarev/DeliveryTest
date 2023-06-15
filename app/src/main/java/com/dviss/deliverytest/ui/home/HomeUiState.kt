package com.dviss.deliverytest.ui.home

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class HomeUiState(
    val location: String = "Unknown location"
) {
    private val currentDate = Calendar.getInstance().time
    private val dateFormat = SimpleDateFormat("d MMMM, yyyy", Locale.getDefault())
    val date: String = dateFormat.format(currentDate)
}
