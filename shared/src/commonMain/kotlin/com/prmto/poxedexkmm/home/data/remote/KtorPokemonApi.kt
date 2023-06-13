package com.prmto.poxedexkmm.home.data.remote

import com.prmto.poxedexkmm.home.data.model.dto.PokemonDto
import com.prmto.poxedexkmm.home.data.model.dto.PokemonList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class KtorPokemonApi(
    private val httpClient: HttpClient
) : PokemonApi {

    override suspend fun getPokemonList(url: String): PokemonList {
        return httpClient.get {
            url(url)
            contentType(ContentType.Application.Json)
        }.body<PokemonList>()
    }

    override suspend fun getPokemon(url: String): PokemonDto {
        return httpClient.get {
            url(url)
            contentType(ContentType.Application.Json)
        }.body<PokemonDto>()
    }
}