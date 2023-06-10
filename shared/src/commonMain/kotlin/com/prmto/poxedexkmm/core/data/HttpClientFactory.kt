package com.prmto.poxedexkmm.core.data

import io.ktor.client.HttpClient

expect class HttpClientFactory {

    fun create(): HttpClient
}