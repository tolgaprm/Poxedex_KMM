package com.prmto.poxedexkmm.home.data.mapper

import com.prmto.poxedexkmm.core.data.pokemonTypes
import com.prmto.poxedexkmm.home.data.model.dto.TypeDto
import com.prmto.poxedexkmm.home.data.util.extractIdFromUrl
import com.prmto.poxedexkmm.home.domain.model.Type

actual fun TypeDto.toType(): Type {
    return Type(
        typeImage = pokemonTypes.filter { it.name.lowercase() == type.name.lowercase() }[0].name.lowercase(),
        id = extractIdFromUrl(type.url),
        name = type.name,
        color = pokemonTypes.filter { it.name.lowercase() == type.name.lowercase() }[0].color,
        textColor = pokemonTypes.filter { it.name.lowercase() == type.name.lowercase() }[0].textColor
    )
}