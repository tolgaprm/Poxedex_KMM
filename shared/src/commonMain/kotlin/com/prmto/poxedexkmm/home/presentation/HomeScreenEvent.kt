package com.prmto.poxedexkmm.home.presentation

import com.prmto.poxedexkmm.core.data.Order
import com.prmto.poxedexkmm.core.data.PokemonType

sealed class HomeScreenEvent {
    data class OnSearchTextChange(val text: String) : HomeScreenEvent()
    data class OnPokemonTypeSelected(val pokemonType: PokemonType) : HomeScreenEvent()
    data class OnOrderSelected(val order: Order) : HomeScreenEvent()
}
