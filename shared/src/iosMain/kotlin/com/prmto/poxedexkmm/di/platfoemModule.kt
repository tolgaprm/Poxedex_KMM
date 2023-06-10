package com.prmto.poxedexkmm.di

import com.prmto.poxedexkmm.core.data.HttpClientFactory
import org.koin.dsl.module

actual fun platformModule() = module {
    single { HttpClientFactory().create() }
}