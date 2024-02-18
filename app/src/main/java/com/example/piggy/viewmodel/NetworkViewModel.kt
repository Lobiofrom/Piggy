package com.example.piggy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.states.States
import com.example.domain.usecase.GetDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NetworkViewModel(
    private val getDataUseCase: GetDataUseCase
) : ViewModel() {
    private var _state = MutableStateFlow<States>(States.Loading)
    val state = _state.asStateFlow()

    init {
        _state.value = States.Loading
        viewModelScope.launch {
            try {
                _state.value = States.Success(getDataUseCase.execute())
            } catch (e: Exception) {
                _state.value = States.Error(e.message)
            }
        }
    }
}