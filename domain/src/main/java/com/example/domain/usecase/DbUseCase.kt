package com.example.domain.usecase

import com.example.domain.model.MoneyBoxFromDb
import com.example.domain.repository.DbRepo
import kotlinx.coroutines.flow.Flow

class DbUseCase(
    private val dbRepo: DbRepo
) {
    suspend fun executeUpsert(moneyBoxFromDb: MoneyBoxFromDb) {
        dbRepo.upsertItem(moneyBoxFromDb)
    }

    fun executeList(): Flow<List<MoneyBoxFromDb>> {
        return dbRepo.getItemList()
    }
}