package com.example.data.dto

import com.example.domain.model.MoneyBoxes
import kotlinx.serialization.Serializable

@Serializable
data class MoneyboxesDto(
    val moneyboxes: List<MoneyboxDto>
) {
    fun MoneyboxesDtoToMoneyboxes() = MoneyBoxes(
        moneyboxes = moneyboxes.map {
            it.moneyboxDtoToMoneybox()
        }
    )
}