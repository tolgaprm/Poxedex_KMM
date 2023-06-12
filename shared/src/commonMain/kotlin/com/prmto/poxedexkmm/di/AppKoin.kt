package com.prmto.poxedexkmm.di

import com.prmto.poxedexkmm.home.data.remote.KtorPokemonApi
import com.prmto.poxedexkmm.home.data.remote.PokemonApi
import com.prmto.poxedexkmm.home.data.repository.PokemonRepositoryImpl
import com.prmto.poxedexkmm.home.domain.repository.PokemonRepository
import com.prmto.poxedexkmm.splash.di.splashModule
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        modules(commonModule(), splashModule, platformModule())
        appDeclaration()
    }

// called by iOS etc
fun initKoin() = initKoin(enableNetworkLogs = false) {}

fun commonModule() = module {
    single { createHttpClient(get()) }
    single<PokemonApi> { KtorPokemonApi(get()) }
    single<PokemonRepository> { PokemonRepositoryImpl(get()) }
}


fun createHttpClient(httpClientEngine: HttpClientEngine) = HttpClient(httpClientEngine) {
    install(ContentNegotiation) {
        json()
    }

    install(HttpCache)
}

