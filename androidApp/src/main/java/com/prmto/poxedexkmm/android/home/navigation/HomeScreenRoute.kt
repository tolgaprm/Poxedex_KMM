package com.prmto.poxedexkmm.android.home.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.prmto.poxedexkmm.android.core.navigation.Screens
import com.prmto.poxedexkmm.android.home.presentation.AndroidHomeViewModel
import org.koin.androidx.compose.getViewModel

fun NavGraphBuilder.homeScreen() {

    composable(Screens.HomeScreen.route) {
        val viewModel = getViewModel<AndroidHomeViewModel>()
        val homeState = viewModel.state.collectAsStateWithLifecycle()

        val pokeom = viewModel.pokemon.collectAsLazyPagingItems()

        LazyColumn {
            items(
                count = pokeom.itemCount,
                key = pokeom.itemKey(),
                contentType = pokeom.itemContentType()
            ) { index ->
                val item = pokeom[index]
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = item?.name ?: "Loading...")
                    Text(text = item?.url ?: "Loading...")
                }
            }
        }
    }
}