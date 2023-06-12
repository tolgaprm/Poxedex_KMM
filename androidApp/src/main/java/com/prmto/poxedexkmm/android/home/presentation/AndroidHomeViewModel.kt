package com.prmto.poxedexkmm.android.home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.prmto.poxedexkmm.android.home.data.PokemonPagingSource
import com.prmto.poxedexkmm.home.data.model.dto.PokemonDto
import com.prmto.poxedexkmm.home.data.remote.PokemonApi
import com.prmto.poxedexkmm.home.domain.model.Pokemon
import com.prmto.poxedexkmm.home.domain.repository.PokemonRepository
import com.prmto.poxedexkmm.home.presentation.HomeScreenEvent
import com.prmto.poxedexkmm.home.presentation.HomeViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AndroidHomeViewModel : ViewModel(), KoinComponent {

    private val ktorPokemonApi: PokemonApi by inject()

    private val viewModel by lazy {
        HomeViewModel(viewModelScope)
    }


    private val _pokeState = MutableStateFlow(emptyList<Pokemon>())
    val pokeState = _pokeState.asStateFlow()


    fun onEvent(event: HomeScreenEvent) {
        viewModel.onEvent(event)
    }

    fun onSearchTextChange(listPoke: List<Pokemon>) {
        _pokeState.value = listPoke.filter {
            it.name.contains("ra")
        }
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