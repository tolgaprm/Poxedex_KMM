package com.prmto.poxedexkmm.android.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.prmto.poxedexkmm.android.home.data.PokemonPagingSource
import com.prmto.poxedexkmm.home.data.remote.PokemonApi
import com.prmto.poxedexkmm.home.presentation.HomeScreenEvent
import com.prmto.poxedexkmm.home.presentation.HomeViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AndroidHomeViewModel : ViewModel(), KoinComponent {

    private val ktorPokemonApi: PokemonApi by inject()

    private val viewModel by lazy {
        HomeViewModel(viewModelScope)
    }

    fun onEvent(event: HomeScreenEvent) {
        viewModel.onEvent(event)
    }

    val state = viewModel.state

    val pokemon = Pager(
        PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            PokemonPagingSource(
                pokemonApi = ktorPokemonApi,
                scope = viewModelScope
            )
        }
    ).flow.cachedIn(viewModelScope)
}