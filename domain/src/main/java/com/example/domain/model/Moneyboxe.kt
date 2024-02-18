package com.example.domain.model

import com.example.domain.entity.Entity
import kotlinx.serialization.Serializable

@Serializable
data class Moneybox(
    val alreadyhave: Int,
    val amount: Int,
    val dateto: String,
    override val id: String,
    val isArchived: Boolean,
    val title: String,
    val unit: String
) : Entity