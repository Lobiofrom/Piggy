package com.example.data.dto

import com.example.domain.model.Moneybox
import kotlinx.serialization.Serializable

@Serializable
data class MoneyboxDto(
    val alreadyhave: Int,
    val amount: Int,
    val dateto: String,
    val id: String,
    val isArchived: Boolean,
    val title: String,
    val unit: String
) {
    fun moneyboxDtoToMoneybox() = Moneybox(
        alreadyhave = alreadyhave,
        amount = amount,
        dateto = dateto,
        id = id,
        isArchived = isArchived,
        title = title,
        unit = unit
    )
}
