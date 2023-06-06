package com.prmto.poxedexkmm.android.on_boarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.prmto.poxedexkmm.android.core.navigation.Screens
import com.prmto.poxedexkmm.android.on_boarding.OnBoardingScreen
import com.prmto.poxedexkmm.on_boarding.presentation.OnBoardingViewModel
import org.koin.androidx.compose.getViewModel

fun NavGraphBuilder.onboardingScreen(
    onNavigateToHome: () -> Unit
) {
    composable(Screens.OnBoardingScreen.route) {
        val viewModel = getViewModel<OnBoardingViewModel>()
        OnBoardingScreen(
            onNavigateToHome = {
                viewModel.onBoardingCompleted()
                onNavigateToHome()
            }
        )
    }
}