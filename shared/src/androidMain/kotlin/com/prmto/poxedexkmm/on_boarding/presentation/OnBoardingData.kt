package com.prmto.poxedexkmm.on_boarding.presentation

import androidx.annotation.DrawableRes
import com.prmto.poxedexkmm.R

actual data class OnBoardingData(
    @DrawableRes val image: Int,
    actual val title: String,
    actual val description: String,
)

actual fun getOnBoardingData() = listOf(
    OnBoardingData(
        image = R.drawable.onboarding_1,
        title = "All Pokémon in One Place",
        description = "Access a vast list of Pokémon from every generation ever made by Nintendo"
    ),
    OnBoardingData(
        image = R.drawable.onboarding_2,
        title = "Search for Pokémon",
        description = "Search for your favorite Pokémon by name"
    )
)