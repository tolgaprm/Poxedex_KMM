package com.prmto.poxedexkmm.android.core.navigation

sealed class Screens(val route: String) {

    object SplashScreen : Screens("splash_screen")
    object OnBoardingScreen : Screens("on_boarding_screen")
    object HomeScreen : Screens("home_screen")
    object FavoriteScreen : Screens("favorite_screen")
}