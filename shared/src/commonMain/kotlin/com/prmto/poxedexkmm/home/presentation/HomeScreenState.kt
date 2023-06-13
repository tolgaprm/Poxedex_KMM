package com.prmto.poxedexkmm.home.presentation

import com.prmto.poxedexkmm.core.data.OrderType
import com.prmto.poxedexkmm.core.data.PokemonType
import com.prmto.poxedexkmm.core.data.pokemonTypes
import com.prmto.poxedexkmm.home.domain.model.Pokemon

data class HomeScreenState(
    val pokemonListOfType: List<PokemonType> = pokemonTypes,
    val searchText: String = "",
    val selectedPokemonType: PokemonType = pokemonListOfType[0],
    val selectedOrderType: OrderType = OrderType.SmallestNumber,
    val pokemonWithSearchAndTypeAndOrder: List<Pokemon> = emptyList(),
    val isSearchOrTypeOrOrderApplied: Boolean = false,
)
