package com.dviss.deliverytest.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dviss.deliverytest.domain.model.CartItem
import com.dviss.deliverytest.domain.model.Dish
import com.dviss.deliverytest.domain.usecase.AppUseCases
import com.dviss.deliverytest.ui.category.CategoryMenuUiState
import com.dviss.deliverytest.ui.home.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val useCases: AppUseCases
) : ViewModel() {

    private val _state = MutableLiveData(CartUiState())
    val state: LiveData<CartUiState> = _state

    init {
        updateLocation()
        observeCartUIItems()
    }

    // determine current device location
    fun updateLocation() {
        viewModelScope.launch {
            val location = useCases.getLocation()
            val currentState = _state.value ?: CartUiState()
            val updatedState = currentState.copy(location = location)
            _state.value = updatedState
        }
    }

    private fun observeCartUIItems() {
        viewModelScope.launch {
            combine(
                useCases.getCartItems(),
                useCases.getDishes()
            ) { cartItems, dishes ->
                createCartUiItems(cartItems, dishes)
            }.collect { cartUiItems ->
                val currentState = _state.value ?: CartUiState()
                val updatedState = currentState.copy(cartUiItems = cartUiItems)
                _state.value = updatedState
            }
        }
    }

    private fun createCartUiItems(
        cartItems: List<CartItem>,
        dishes: List<Dish>
    ): List<CartItemUiModel> {
        return cartItems.mapNotNull { cartItem ->
            val dish = dishes.find { it.id == cartItem.id }
            dish?.let {
                CartItemUiModel(
                    id = dish.id,
                    name = dish.name,
                    price = dish.price.toString() + " ₽",
                    weight = " · " + dish.weight.toString() + "г",
                    imageUrl = dish.imageUrl,
                    count = cartItem.number
                )
            }
        }
    }

    fun plusItem(item: CartItemUiModel) {
        viewModelScope.launch {
            useCases.editCartItemCount(item.toCartItem(), item.count + 1)
        }
    }

    fun minusItem(item: CartItemUiModel) {
        viewModelScope.launch {
            useCases.editCartItemCount(item.toCartItem(), item.count - 1)
        }
    }
}