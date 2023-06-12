package com.prmto.poxedexkmm.android.home.navigation

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.prmto.poxedexkmm.android.core.navigation.Screens
import com.prmto.poxedexkmm.android.home.presentation.AndroidHomeViewModel
import com.prmto.poxedexkmm.android.home.presentation.HomeScreen
import org.koin.androidx.compose.getViewModel

fun NavGraphBuilder.homeScreen() {

    composable(Screens.HomeScreen.route) {
        val viewModel = getViewModel<AndroidHomeViewModel>()
        val homeState = viewModel.state.collectAsStateWithLifecycle()
        val pokemonPagingItems = viewModel.pokemon.collectAsLazyPagingItems()

        HomeScreen(
            homeState = homeState.value,
            pokemonPagingItems = pokemonPagingItems,
            onEvent = viewModel::onEvent
        )
    }
}