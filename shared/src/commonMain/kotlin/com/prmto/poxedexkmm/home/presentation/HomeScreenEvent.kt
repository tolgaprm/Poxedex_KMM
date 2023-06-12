package com.prmto.poxedexkmm.home.presentation

import com.prmto.poxedexkmm.core.data.OrderType
import com.prmto.poxedexkmm.core.data.PokemonType
import com.prmto.poxedexkmm.home.domain.model.Pokemon

sealed class HomeScreenEvent {
    data class OnSearchTextChange(val text: String, val pagingPokemonList: List<Pokemon>) :
        HomeScreenEvent()

    data class OnPokemonTypeSelected(
        val pokemonType: PokemonType,
        val pagingPokemonList: List<Pokemon>
    ) : HomeScreenEvent()

    data class OnOrderSelected(val order: OrderType, val pagingPokemonList: List<Pokemon>) :
        HomeScreenEvent()

    data class onFavoriteClicked(val pokemon: Pokemon) : HomeScreenEvent()
}
