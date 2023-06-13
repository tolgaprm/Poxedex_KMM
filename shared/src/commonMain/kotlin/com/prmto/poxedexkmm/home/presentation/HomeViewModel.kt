package com.prmto.poxedexkmm.home.presentation

import com.prmto.poxedexkmm.core.data.OrderType
import com.prmto.poxedexkmm.core.data.PokemonType
import com.prmto.poxedexkmm.home.domain.model.Pokemon
import com.prmto.poxedexkmm.home.domain.repository.PokemonRepository
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel(
    coroutineScope: CoroutineScope? = null
) : KoinComponent {

    val pokemonRepository: PokemonRepository by inject()


    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(HomeScreenState())

    @NativeCoroutines
    val state = _state.asStateFlow()


    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.OnSearchTextChange -> onSearchTextChange(
                event.text,
                pagingSnapshot = event.pagingPokemonList
            )

            is HomeScreenEvent.OnPokemonTypeSelected -> onPokemonTypeSelected(
                event.pokemonType,
                pagingSnapshot = event.pagingPokemonList
            )

            is HomeScreenEvent.OnOrderSelected -> onOrderSelected(
                event.order,
                pagingSnapshot = event.pagingPokemonList
            )

            is HomeScreenEvent.onFavoriteClicked -> onFavoriteClicked(event.pokemon)
        }
    }

    private fun onSearchTextChange(text: String, pagingSnapshot: List<Pokemon>) {
        _state.update {
            it.copy(
                searchText = text
            )
        }
        onChangePokemonList(pagingSnapshot = pagingSnapshot)
    }

    private fun onPokemonTypeSelected(pokemonType: PokemonType, pagingSnapshot: List<Pokemon>) {
        _state.update {
            it.copy(
                selectedPokemonType = pokemonType
            )
        }
        onChangePokemonList(pagingSnapshot = pagingSnapshot)
    }

    private fun onOrderSelected(order: OrderType, pagingSnapshot: List<Pokemon>) {
        _state.update {
            it.copy(
                selectedOrderType = order
            )
        }
        onChangePokemonList(pagingSnapshot = pagingSnapshot)
    }

    private fun onChangePokemonList(pagingSnapshot: List<Pokemon>) {
        if (state.value.searchText.isEmpty()) {
            val pokemonList = filterPokemonWithSelectedType(pagingSnapshot)

            val pokemonListOrdered = getOrderedPokemonList(pokemonList)

            _state.update { it.copy(pokemonWithSearchAndTypeAndOrder = pokemonListOrdered) }
        } else {
            val pokemonList = filterPokemonWithSelectedType(pagingSnapshot)

            val pokemonListOrdered = getOrderedPokemonList(pokemonList)

            val pokemonListFiltered = pokemonListOrdered.filter { pokemon ->
                pokemon.name.contains(state.value.searchText, ignoreCase = true)
            }

            _state.update { it.copy(pokemonWithSearchAndTypeAndOrder = pokemonListFiltered) }
        }

        _state.update {
            it.copy(
                isSearchOrTypeOrOrderApplied =
                state.value.searchText.isNotEmpty()
                        || state.value.selectedPokemonType.id != 0
                        || state.value.selectedOrderType != OrderType.SmallestNumber
            )
        }
    }

    private fun getOrderedPokemonList(pokemonList: List<Pokemon>): List<Pokemon> {
        return when (state.value.selectedOrderType) {
            OrderType.SmallestNumber -> {
                pokemonList.sortedBy { it.id }
            }

            OrderType.LargestNumber -> {
                pokemonList.sortedByDescending { it.id }
            }

            OrderType.AZ -> {
                pokemonList.sortedBy { it.name }
            }

            OrderType.ZA -> {
                pokemonList.sortedByDescending { it.name }
            }
        }
    }

    private fun filterPokemonWithSelectedType(pokemonList: List<Pokemon>): List<Pokemon> {
        if (state.value.selectedPokemonType.id == 0) {
            return pokemonList
        }
        return pokemonList.filter { pokemon ->
            pokemon.type.any { it.id == state.value.selectedPokemonType.id }
        }
    }


    // Only for ios
    fun onIosViewModelCreated(state: HomeScreenState) {
        _state.update { state }
    }

    private fun onFavoriteClicked(pokemon: Pokemon) {
        TODO("After implementing the ios side, ı'll implement the SqlDelight")
    }
}