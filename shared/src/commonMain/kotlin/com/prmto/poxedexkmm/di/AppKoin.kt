package com.prmto.poxedexkmm.di

import com.prmto.poxedexkmm.home.data.repository.KtorPokemonClient
import com.prmto.poxedexkmm.home.domain.repository.PokemonClient
import com.prmto.poxedexkmm.splash.di.splashModule
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
    single<PokemonClient> { KtorPokemonClient(get()) }
}

