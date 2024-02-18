package com.example.data.repositoryimpl

import com.example.data.dto.MoneyboxesDto
import com.example.domain.model.Moneybox
import com.example.domain.repository.Repository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url


class RepositoryImpl(
    private val api: HttpClient
) : Repository {
    override suspend fun getData(): List<Moneybox> {
        val moneyBoxesDto: MoneyboxesDto = api.get {
            url("https://run.mocky.io/v3/a3ffe944-a095-4ba1-9092-7feff03e5c09")
        }.body()
        return moneyBoxesDto.MoneyboxesDtoToMoneyboxes().moneyboxes
    }
}