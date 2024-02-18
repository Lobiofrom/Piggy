package com.example.domain.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.example.domain.entity.Entity

@androidx.room.Entity
data class MoneyBoxFromDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "autoId")
    val autoId: Int = 0,
    @ColumnInfo(name = "id")
    override val id: String = "",
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "goalSum")
    val goalSum: Int,
    @ColumnInfo(name = "alreadyHave")
    val alreadyHave: Int = 0,
    @ColumnInfo(name = "date")
    val date: String
) : Entity
