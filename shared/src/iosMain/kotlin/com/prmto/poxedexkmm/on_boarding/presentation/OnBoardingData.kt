package com.prmto.poxedexkmm.on_boarding.presentation

actual data class OnBoardingData(
    val image: String,
    actual val title: String,
    actual val description: String
)

actual fun getOnBoardingData() = listOf(
    OnBoardingData(
        image = "onboarding_1",
        title = "All Pokémon in One Place",
        description = "Access a vast list of Pokémon from every generation ever made by Nintendo"
    ),
    OnBoardingData(
        image = "onboarding_2",
        title = "Search for Pokémon",
        description = "Search for your favorite Pokémon by name"
    )
)