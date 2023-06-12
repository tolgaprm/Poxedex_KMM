package com.prmto.poxedexkmm.home.data.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class PokemonList(
    val count: Int,
    val next: String?,
    val previous: String? = null,
    val results: List<Result>
)

@Serializable
data class Result(
    val name: String,
    val url: String
)
