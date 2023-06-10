package com.prmto.poxedexkmm.home.presentation

import com.prmto.poxedexkmm.core.data.Order
import com.prmto.poxedexkmm.core.data.PokemonType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel(
    coroutineScope: CoroutineScope? = null
) {

    val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(
        HomeScreenState()
    )

    val state = _state.asStateFlow()

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.OnSearchTextChange -> onSearchTextChange(event.text)
            is HomeScreenEvent.OnPokemonTypeSelected -> onPokemonTypeSelected(event.pokemonType)
            is HomeScreenEvent.OnOrderSelected -> onOrderSelected(event.order)
        }
    }

    private fun onSearchTextChange(text: String) {
        _state.update { it.copy(searchText = text) }
    }

    private fun onPokemonTypeSelected(pokemonType: PokemonType) {
        _state.update { it.copy(selectedPokemonType = pokemonType) }
    }

    private fun onOrderSelected(order: Order) {
        _state.update { it.copy(selectedOrderType = order) }
    }
}