package com.prmto.poxedexkmm.android.home.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.prmto.poxedexkmm.home.data.mapper.toPokemon
import com.prmto.poxedexkmm.home.data.remote.PokemonApi
import com.prmto.poxedexkmm.home.domain.model.Pokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

class PokemonPagingSource(
    private val pokemonApi: PokemonApi,
    private val scope: CoroutineScope
) : PagingSource<String, Pokemon>() {

    override fun getRefreshKey(state: PagingState<String, Pokemon>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Pokemon> {
        val nextPage = params.key ?: "https://pokeapi.co/api/v2/pokemon"
        val response = pokemonApi.getPokemonList(nextPage)

        val pokemon = response.results.map { result ->
            scope.async(Dispatchers.IO) {
                pokemonApi.getPokemon(result.url)
            }
        }.awaitAll()

        return LoadResult.Page(
            data = pokemon.map { it.toPokemon() },
            prevKey = response.previous,
            nextKey = response.next
        )
    }
}