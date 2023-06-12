package com.prmto.poxedexkmm.home.domain.repository

import com.kuuurt.paging.multiplatform.PagingData
import com.prmto.poxedexkmm.home.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonPaging(): Flow<PagingData<Pokemon>>
}