package com.example.domain.repository

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.domain.model.MoneyBoxFromDb
import kotlinx.coroutines.flow.Flow

@Dao
interface DbRepo {
    @Upsert
    suspend fun upsertItem(itemFromDb: MoneyBoxFromDb)

    @Query("select * from MoneyBoxFromDb")
    fun getItemList(): Flow<List<MoneyBoxFromDb>>
}