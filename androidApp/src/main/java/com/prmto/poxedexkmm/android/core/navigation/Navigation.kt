package com.prmto.poxedexkmm.android.core.navigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.prmto.poxedexkmm.android.home.navigation.homeScreen
import com.prmto.poxedexkmm.android.on_boarding.navigation.onboardingScreen
import com.prmto.poxedexkmm.android.splash.navigation.splashScreen

@Composable
fun PoxedexNavigation(
    navController: NavHostController
) {

    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {

        splashScreen(
            onNavigateToHome = {
                navController.navigate(Screens.HomeScreen.route) {
                    popUpToInclusive(Screens.SplashScreen.route)
                }
            },
            onNavigateToOnBoarding = {
                navController.navigate(Screens.OnBoardingScreen.route) {
                    popUpToInclusive(Screens.SplashScreen.route)
                }
            }
        )

        onboardingScreen(
            onNavigateToHome = {
                navController.navigate(Screens.HomeScreen.route) {
                    popUpToInclusive(Screens.OnBoardingScreen.route)
                }
            }
        )

        homeScreen()

        composable(Screens.FavoriteScreen.route) {
            Text(text = "Favorite Screen")
        }
    }
}


fun NavOptionsBuilder.popUpToInclusive(route: String) {
    popUpTo(route) { inclusive = true }
}
