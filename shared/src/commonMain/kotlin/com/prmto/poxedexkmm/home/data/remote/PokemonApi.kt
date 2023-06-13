package com.prmto.poxedexkmm.home.data.remote

import com.prmto.poxedexkmm.home.data.model.dto.PokemonDto
import com.prmto.poxedexkmm.home.data.model.dto.PokemonList

interface PokemonApi {

    suspend fun getPokemonList(url: String): PokemonList

    suspend fun getPokemon(url: String): PokemonDto
}