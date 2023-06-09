package com.prmto.poxedexkmm.home.data.repository

import com.kuuurt.paging.multiplatform.Pager
import com.kuuurt.paging.multiplatform.PagingConfig
import com.kuuurt.paging.multiplatform.PagingResult
import com.kuuurt.paging.multiplatform.helpers.cachedIn
import com.prmto.poxedexkmm.core.util.asCommonFlow
import com.prmto.poxedexkmm.home.data.mapper.toPokemon
import com.prmto.poxedexkmm.home.data.remote.PokemonApi
import com.prmto.poxedexkmm.home.data.util.Util.BASE_URL_POKEMON
import com.prmto.poxedexkmm.home.domain.PagingError
import com.prmto.poxedexkmm.home.domain.PagingException
import com.prmto.poxedexkmm.home.domain.model.Pokemon
import com.prmto.poxedexkmm.home.domain.repository.PokemonRepository
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

class PokemonRepositoryImpl(
    private val pokemonApi: PokemonApi
) : PokemonRepository {

    private val scope = MainScope()

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    override val getPokemonPaging
        get() = try {
            getPokemonPager.pagingData.cachedIn(scope = scope)
        } catch (e: IOException) {
            throw PagingException(PagingError.SERVICE_UNAVAILABLE)
        } catch (e: Exception) {
            throw PagingException(PagingError.UNKNOWN_ERROR)
        }.asCommonFlow()

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    override val getPokemonPager: Pager<String, Pokemon>
        get() =
            Pager<String, Pokemon>(
                clientScope = scope,
                config = PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = false
                ),
                initialKey = BASE_URL_POKEMON,
                getItems = { currentKey, _ ->
                    val response = pokemonApi.getPokemonList(currentKey)

                    val pokemon = response.results.map { result ->
                        scope.async {
                            pokemonApi.getPokemon(result.url)
                        }
                    }.awaitAll()

                    PagingResult(
                        items = pokemon.map { it.toPokemon() },
                        currentKey = currentKey,
                        prevKey = { response.previous },
                        nextKey = { response.next }
                    )
                }
            )
}