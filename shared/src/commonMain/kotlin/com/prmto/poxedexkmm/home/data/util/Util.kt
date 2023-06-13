package com.prmto.poxedexkmm.home.data.util

fun extractIdFromUrl(url: String): Int {
    var id = 0
    Regex("(\\d+)/\$").find(url)?.let { matchResult ->
        id = matchResult.groupValues[1].toInt()
    }
    return id
}

object Util {
    const val BASE_URL = "https://pokeapi.co/api/v2/"
    const val BASE_URL_POKEMON = "https://pokeapi.co/api/v2/pokemon/"
}