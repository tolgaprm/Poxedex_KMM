package com.prmto.poxedexkmm.on_boarding.presentation

expect class OnBoardingData {
    val title: String
    val description: String
}


expect fun getOnBoardingData(): List<OnBoardingData>