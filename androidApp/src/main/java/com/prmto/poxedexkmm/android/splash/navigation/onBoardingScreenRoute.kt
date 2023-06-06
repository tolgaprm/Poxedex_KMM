package com.prmto.poxedexkmm.android.splash.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.prmto.poxedexkmm.android.core.navigation.Screens
import com.prmto.poxedexkmm.android.splash.SplashScreen
import com.prmto.poxedexkmm.splash.presentation.SplashViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.getViewModel

fun NavGraphBuilder.splashScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToOnBoarding: () -> Unit
) {

    composable(Screens.SplashScreen.route) {
        val viewModel = getViewModel<SplashViewModel>()
        SplashScreen()

        val onBoardingCompleted = viewModel.onBoardingCompleted.collectAsState()

        LaunchedEffect(key1 = true) {
            delay(1000)
            if (onBoardingCompleted.value) {
                onNavigateToHome()
            } else {
                onNavigateToOnBoarding()
            }
        }
    }
}