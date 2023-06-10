package com.prmto.poxedexkmm.home.data.repository

import com.kuuurt.paging.multiplatform.Pager
import com.kuuurt.paging.multiplatform.PagingConfig
import com.kuuurt.paging.multiplatform.PagingData
import com.kuuurt.paging.multiplatform.PagingResult
import com.kuuurt.paging.multiplatform.helpers.cachedIn
import com.prmto.poxedexkmm.home.data.responses.Pokemon
import com.prmto.poxedexkmm.home.data.responses.PokemonList
import com.prmto.poxedexkmm.home.data.responses.Result
import com.prmto.poxedexkmm.home.domain.repository.PokemonClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow

class KtorPokemonClient(
    private val httpClient: HttpClient
) : PokemonClient {

    private val scope = MainScope()


    override fun getPokemonPaging(): Flow<PagingData<Result>> {
        return Pager<String, Result>(
            clientScope = scope,
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            initialKey = "https://pokeapi.co/api/v2/pokemon",
            getItems = { currentKey, size ->
                val response = getPokemonList(currentKey)
                PagingResult(
                    items = response.results,
                    currentKey = currentKey,
                    prevKey = { response.previous },
                    nextKey = { response.next }
                )
            }
        ).pagingData.cachedIn(scope = scope)
    }

    override suspend fun getPokemonList(url: String): PokemonList {
        return httpClient.get {
            url(url)
            contentType(ContentType.Application.Json)
        }.body<PokemonList>()
    }

    override suspend fun getPokemon(name: String): Pokemon {
        return httpClient.get {
            url("https://pokeapi.co/api/v2/pokemon/$name")
            contentType(ContentType.Application.Json)
        }.body<Pokemon>()
    }
}