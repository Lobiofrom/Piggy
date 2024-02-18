package com.example.data.states

import com.example.domain.model.Moneybox

sealed class States {
    data class Success(val list: List<Moneybox>) : States()
    data class Error(val error: String?) : States()
    data object Loading : States()
}