package com.prmto.poxedexkmm.core.data

import com.prmto.poxedexkmm.core.presentation.Colors

data class PokemonType(
    val id: Int,
    val name: String,
    val color: Long,
    val textColor: Long = Colors.black
)

val pokemonTypes = listOf(
    PokemonType(0, "All Types", Colors.AllTypes, Colors.white),
    PokemonType(1, "Normal", Colors.Normal, Colors.white),
    PokemonType(2, "Fighting", Colors.Fighter, Colors.white),
    PokemonType(3, "Flying", Colors.Flying),
    PokemonType(4, "Poison", Colors.Poisonous, Colors.white),
    PokemonType(5, "Ground", Colors.Earthy),
    PokemonType(6, "Rock", Colors.Rock),
    PokemonType(7, "Bug", Colors.Insect),
    PokemonType(8, "Ghost", Colors.Ghost, Colors.white),
    PokemonType(9, "Steel", Colors.Metal),
    PokemonType(10, "Fire", Colors.Fire),
    PokemonType(11, "Water", Colors.Water, Colors.white),
    PokemonType(12, "Grass", Colors.Grass),
    PokemonType(13, "Electric", Colors.Electric),
    PokemonType(14, "Psychic", Colors.Psychic),
    PokemonType(15, "Ice", Colors.Ice),
    PokemonType(16, "Dragon", Colors.Dragon, Colors.white),
    PokemonType(17, "Dark", Colors.NightFighter, Colors.white),
    PokemonType(18, "Fairy", Colors.Fairy)
).sortedBy { it.name }


enum class OrderType(id: Int, name: String) {
    SmallestNumber(0, "Smallest number"),
    LargestNumber(1, "Largest number"),
    AZ(2, "A-Z"),
    ZA(3, "Z-A"),
}