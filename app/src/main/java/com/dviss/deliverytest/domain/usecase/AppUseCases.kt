package com.dviss.deliverytest.domain.usecase

data class AppUseCases(
    val getLocation: GetLocation,
    val getCategories: GetCategories,
    val downloadCategories: DownloadCategories,
    val getDishes: GetDishes,
    val downloadDishes: DownloadDishes,
    val addDishToCart: AddDishToCart,
    val getCartItems: GetCartItems,
    val editCartItemCount: EditCartItemCount,
)
