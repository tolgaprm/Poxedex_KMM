package com.prmto.poxedexkmm.android.home.navigation

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.prmto.poxedexkmm.android.core.navigation.Screens
import com.prmto.poxedexkmm.android.home.presentation.HomeScreen
import com.prmto.poxedexkmm.home.presentation.HomeViewModel
import org.koin.androidx.compose.getViewModel

fun NavGraphBuilder.homeScreen() {

    composable(Screens.HomeScreen.route) {
        val viewModel = getViewModel<HomeViewModel>()
        val homeState = viewModel.state.collectAsStateWithLifecycle()

        HomeScreen(
            homeState = homeState.value,
            onEvent = viewModel::onEvent
        )
    }
}