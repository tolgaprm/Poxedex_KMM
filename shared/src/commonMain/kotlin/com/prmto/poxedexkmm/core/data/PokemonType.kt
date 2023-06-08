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
    PokemonType(1, "Normal", Colors.Normal),
    PokemonType(2, "Fighting", Colors.Fighter, Colors.white),
    PokemonType(3, "Flying", Colors.Flying),
    PokemonType(4, "Poison", Colors.Poisonous),
    PokemonType(5, "Ground", Colors.Earthy),
    PokemonType(6, "Rock", Colors.Rock),
    PokemonType(7, "Bug", Colors.Insect),
    PokemonType(8, "Ghost", Colors.Ghost, Colors.white),
    PokemonType(9, "Steel", Colors.Metal),
    PokemonType(10, "Fire", Colors.Fire),
    PokemonType(11, "Water", Colors.Water),
    PokemonType(12, "Grass", Colors.Grass),
    PokemonType(13, "Electric", Colors.Electric),
    PokemonType(14, "Psychic", Colors.Psychic),
    PokemonType(15, "Ice", Colors.Ice),
    PokemonType(16, "Dragon", Colors.Dragon, Colors.white),
    PokemonType(17, "Night Fighter", Colors.NightFighter, Colors.white),
    PokemonType(18, "Fairy", Colors.Fairy)
).sortedBy { it.name }

data class Order(
    val id: Int,
    val name: String
)

val order = listOf(
    Order(0, "Smallest number"),
    Order(1, "Largest number"),
    Order(2, "A-Z"),
    Order(3, "Z-A"),
)