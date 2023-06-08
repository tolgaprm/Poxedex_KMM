package com.prmto.poxedexkmm.home.presentation

import com.prmto.poxedexkmm.core.data.Order
import com.prmto.poxedexkmm.core.data.PokemonType
import com.prmto.poxedexkmm.core.data.order
import com.prmto.poxedexkmm.core.data.pokemonTypes

data class HomeScreenState(
    val pokemonListOfType: List<PokemonType> = pokemonTypes,
    val searchText: String = "",
    val selectedPokemonType: PokemonType = pokemonListOfType[0],
    val selectedOrderType: Order = order[0]
)
