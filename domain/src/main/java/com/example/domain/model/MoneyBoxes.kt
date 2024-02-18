package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MoneyBoxes(
    val moneyboxes: List<Moneybox>
)