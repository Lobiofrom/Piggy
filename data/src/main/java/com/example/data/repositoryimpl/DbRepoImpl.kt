package com.example.data.repositoryimpl

import com.example.data.database.MyDataBase
import com.example.domain.model.MoneyBoxFromDb
import com.example.domain.repository.DbRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class DbRepoImpl(
    private val myDataBase: MyDataBase
) : DbRepo {
    override suspend fun upsertItem(itemFromDb: MoneyBoxFromDb) {
        withContext(Dispatchers.IO) {
            myDataBase.itemDao().upsertItem(itemFromDb)
        }
    }

    override fun getItemList(): Flow<List<MoneyBoxFromDb>> {
        return myDataBase.itemDao().getItemList()
    }
}