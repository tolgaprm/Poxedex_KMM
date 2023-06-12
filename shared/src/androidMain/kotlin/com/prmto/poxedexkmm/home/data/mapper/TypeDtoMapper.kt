package com.prmto.poxedexkmm.home.data.mapper

import com.prmto.poxedexkmm.R
import com.prmto.poxedexkmm.core.data.pokemonTypes
import com.prmto.poxedexkmm.core.presentation.Colors
import com.prmto.poxedexkmm.home.data.model.dto.TypeDto
import com.prmto.poxedexkmm.home.data.util.extractIdFromUrl
import com.prmto.poxedexkmm.home.domain.model.Type

actual fun TypeDto.toType(): Type {
    return Type(
        typeImageRes = getTypeImageRes(type.name),
        id = extractIdFromUrl(type.url),
        name = type.name,
        color = if (getPokemonType(type.name).isNotEmpty()) getPokemonType(type.name)[0].color else Colors.AllTypes,
        textColor = if (getPokemonType(type.name).isNotEmpty()) getPokemonType(type.name)[0].textColor else Colors.AllTypes
    )
}

private fun getPokemonType(typeName: String) =
    pokemonTypes.filter { it.name.lowercase() == typeName.lowercase() }

fun getTypeImageRes(typeName: String): Int {

    return when (typeName.lowercase()) {
        "normal" -> R.drawable.normal
        "fighting" -> R.drawable.fighting
        "flying" -> R.drawable.flying
        "poison" -> R.drawable.poison
        "ground" -> R.drawable.ground
        "rock" -> R.drawable.rock
        "bug" -> R.drawable.bug
        "ghost" -> R.drawable.ghost
        "steel" -> R.drawable.steel
        "fire" -> R.drawable.fire
        "water" -> R.drawable.water
        "grass" -> R.drawable.grass
        "electric" -> R.drawable.electric
        "psychic" -> R.drawable.psychic
        "ice" -> R.drawable.ice
        "dragon" -> R.drawable.dragon
        "dark" -> R.drawable.dark
        "fairy" -> R.drawable.fairy
        else -> R.drawable.normal
    }
}