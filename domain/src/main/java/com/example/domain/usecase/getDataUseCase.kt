package com.example.domain.usecase

import com.example.domain.model.Moneybox
import com.example.domain.repository.Repository

class GetDataUseCase(
    private val repository: Repository
) {
    suspend fun execute(): List<Moneybox> {
        return repository.getData()
    }
}