package com.example.piggy.di

import com.example.data.database.MyDataBase
import com.example.data.repositoryimpl.DbRepoImpl
import com.example.data.repositoryimpl.RepositoryImpl
import com.example.domain.repository.DbRepo
import com.example.domain.repository.Repository
import com.example.domain.usecase.DbUseCase
import com.example.domain.usecase.GetDataUseCase
import com.example.piggy.viewmodel.DbViewModel
import com.example.piggy.viewmodel.NetworkViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val module = module {
    single {
        MyDataBase.getDatabase(androidContext())
    }
    single {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json()
            }
            install(Logging) {
                level = LogLevel.ALL
            }
        }
    }
    single<DbRepo> { DbRepoImpl(get()) }

    single<Repository> { RepositoryImpl(get()) }

    factory { GetDataUseCase(get()) }
    factory { DbUseCase(get()) }

    viewModel { NetworkViewModel(get()) }
    viewModel { DbViewModel(get()) }

}