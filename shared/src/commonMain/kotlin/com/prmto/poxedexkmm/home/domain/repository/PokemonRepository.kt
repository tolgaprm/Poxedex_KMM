package com.prmto.poxedexkmm.home.domain.repository

import com.kuuurt.paging.multiplatform.Pager
import com.kuuurt.paging.multiplatform.PagingData
import com.prmto.poxedexkmm.core.util.CommonFlow
import com.prmto.poxedexkmm.home.domain.model.Pokemon
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

interface PokemonRepository {
    val getPokemonPaging: CommonFlow<PagingData<Pokemon>>

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val getPokemonPager: Pager<String, Pokemon>
}