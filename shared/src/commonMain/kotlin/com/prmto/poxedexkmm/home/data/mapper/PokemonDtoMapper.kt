package com.prmto.poxedexkmm.home.data.mapper

import com.prmto.poxedexkmm.home.data.model.dto.PokemonDto
import com.prmto.poxedexkmm.home.domain.model.Pokemon

fun PokemonDto.toPokemon(): Pokemon {
    return Pokemon(
        id = id,
        name = name,
        type = if (types.isNotEmpty()) types.map { it.toType() } else emptyList(),
        frontDefaultUrl = this.sprites.frontDefault
    )
}