package com.prmto.poxedexkmm.android.home.presentation

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.prmto.poxedexkmm.home.data.responses.Result
import com.prmto.poxedexkmm.home.domain.repository.PokemonClient
import org.koin.core.component.KoinComponent

class DataSource(
    private val pokemonClient: PokemonClient
) : PagingSource<String, Result>(), KoinComponent {

    override fun getRefreshKey(state: PagingState<String, Result>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Result> {
        return try {
            val nextPage = params.key ?: "https://pokeapi.co/api/v2/pokemon?offset=0&limit=20"
            val response = pokemonClient.getPokemonList(nextPage)
            Log.d("DataSource", "load: ${response.results}")
            LoadResult.Page(
                data = response.results,
                prevKey = response.previous,
                nextKey = response.next
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}