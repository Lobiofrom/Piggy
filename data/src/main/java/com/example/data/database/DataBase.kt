package com.example.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.domain.model.MoneyBoxFromDb
import com.example.domain.repository.DbRepo

@Database(
    entities = [
        MoneyBoxFromDb::class
    ],
    version = 2,
    exportSchema = false
)
abstract class MyDataBase : RoomDatabase() {

    abstract fun itemDao(): DbRepo

    companion object {
        @Volatile
        private var INSTANCE: MyDataBase? = null

        fun getDatabase(
            context: Context
        ): MyDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDataBase::class.java,
                    "DataBase"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}