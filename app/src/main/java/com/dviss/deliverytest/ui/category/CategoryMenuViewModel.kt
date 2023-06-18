package com.dviss.deliverytest.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dviss.deliverytest.domain.model.Dish
import com.dviss.deliverytest.domain.usecase.AppUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryMenuViewModel @Inject constructor(
    private val useCases: AppUseCases
) : ViewModel() {

    private val _state = MutableLiveData(CategoryMenuUiState())
    val state: LiveData<CategoryMenuUiState> = _state
    init {
        downloadDishes()
        observeDishes()
    }

    // observe categories flow from getCategories UseCase in my livedata state
    private fun observeDishes() {
        viewModelScope.launch {
            useCases.getDishes().collect { dishes ->
                val currentState = _state.value ?: CategoryMenuUiState()
                val updatedState = currentState.copy(dishes = dishes)
                _state.value = updatedState
            }
        }
    }

    // download categories from server
    private fun downloadDishes() {
        viewModelScope.launch {
            useCases.downloadDishes()
        }
    }

    fun setCategoryTitle(categoryTitle: String) {
        val currentState = _state.value ?: CategoryMenuUiState()
        val updatedState = currentState.copy(categoryTitle = categoryTitle)
        _state.value = updatedState
    }

    fun addDishToCart(dish: Dish) {
        viewModelScope.launch {
            useCases.addDishToCart(dish)
        }
    }

    fun updateSelectedTag(tag: String) {
        val currentState = _state.value ?: CategoryMenuUiState()
        val updatedState = currentState.copy(selectedTag = tag)
        _state.value = updatedState
    }
}