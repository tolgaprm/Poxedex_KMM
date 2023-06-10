package com.prmto.poxedexkmm.home.domain.repository

import com.kuuurt.paging.multiplatform.PagingData
import com.prmto.poxedexkmm.home.data.responses.Pokemon
import com.prmto.poxedexkmm.home.data.responses.PokemonList
import com.prmto.poxedexkmm.home.data.responses.Result
import kotlinx.coroutines.flow.Flow

interface PokemonClient {
    fun getPokemonPaging(): Flow<PagingData<Result>>

    suspend fun getPokemonList(url: String): PokemonList

    suspend fun getPokemon(name: String): Pokemon
}