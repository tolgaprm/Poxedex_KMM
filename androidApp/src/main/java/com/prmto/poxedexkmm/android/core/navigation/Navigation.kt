package com.prmto.poxedexkmm.android.core.navigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.prmto.poxedexkmm.android.splash.navigation.splashScreen

@Composable
fun PoxedexNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {

        splashScreen(
            onNavigateToHome = {
                navController.navigate(Screens.HomeScreen.route) {
                    popUpTo(Screens.SplashScreen.route) { inclusive = true }
                }
            },
            onNavigateToOnBoarding = {
                navController.navigate(Screens.OnBoardingScreen.route) {
                    popUpTo(Screens.SplashScreen.route) { inclusive = true }
                }
            }
        )

        composable(Screens.OnBoardingScreen.route) {
            Text(text = "OnBoardingScreen ")
        }

        composable(Screens.HomeScreen.route) {
            Text(text = "HomeScreen ")
        }
    }
}

