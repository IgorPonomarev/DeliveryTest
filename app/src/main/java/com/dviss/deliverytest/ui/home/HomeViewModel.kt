package com.dviss.deliverytest.ui.home

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dviss.deliverytest.domain.usecase.AppUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: AppUseCases
) : ViewModel() {

    private val _state = MutableLiveData(HomeUiState())
    val state: LiveData<HomeUiState> = _state

    init {
        updateLocation()
    }

    fun updateLocation() {
        viewModelScope.launch {
            val location = useCases.getLocation()
            val currentState = _state.value ?: HomeUiState()
            val updatedState = currentState.copy(location = location)
            _state.value = updatedState
        }
    }
}