package com.example.domain.repository

import com.example.domain.model.Moneybox

interface Repository {
    suspend fun getData(): List<Moneybox>
}