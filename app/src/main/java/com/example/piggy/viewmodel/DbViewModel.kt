package com.example.piggy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.MoneyBoxFromDb
import com.example.domain.usecase.DbUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DbViewModel(
    private val dbUseCase: DbUseCase,
) : ViewModel() {

    val list = this.dbUseCase.executeList()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun upsert(
        title: String,
        goalSum: Int,
        alreadyHave: Int,
        date: String
    ) {
        viewModelScope.launch {
            val item = MoneyBoxFromDb(
                title = title,
                goalSum = goalSum,
                alreadyHave = alreadyHave,
                date = date
            )
            dbUseCase.executeUpsert(item)
        }
    }
}
