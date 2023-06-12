package com.prmto.poxedexkmm.android.home.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.prmto.poxedexkmm.android.R
import com.prmto.poxedexkmm.android.home.presentation.components.PokemonItem
import com.prmto.poxedexkmm.android.home.presentation.components.PoxedexTextField
import com.prmto.poxedexkmm.android.home.presentation.components.SelectionType
import com.prmto.poxedexkmm.android.home.presentation.components.SheetContent
import com.prmto.poxedexkmm.core.data.OrderType
import com.prmto.poxedexkmm.core.data.PokemonType
import com.prmto.poxedexkmm.core.presentation.Colors
import com.prmto.poxedexkmm.home.domain.model.Pokemon
import com.prmto.poxedexkmm.home.presentation.HomeScreenEvent
import com.prmto.poxedexkmm.home.presentation.HomeScreenState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    homeState: HomeScreenState,
    pokemonPagingItems: LazyPagingItems<Pokemon>,
    onEvent: (HomeScreenEvent) -> Unit,
) {
    val isShowPokemonTypes = remember {
        mutableStateOf(false)
    }

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(
            initialValue = BottomSheetValue.Collapsed
        )
    )
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp),
        scaffoldState = bottomSheetScaffoldState,
        sheetGesturesEnabled = false,
        sheetContent = {
            SheetContent(
                allPokemonTypes = homeState.pokemonListOfType,
                isShowPokemonTypes = isShowPokemonTypes.value,
                onPokemonTypeSelected = { pokemonType ->
                    onEvent(
                        HomeScreenEvent.OnPokemonTypeSelected(
                            pokemonType = pokemonType,
                            pagingPokemonList = pokemonPagingItems.itemSnapshotList.items
                        )
                    )
                    coroutineScope.launch {
                        bottomSheetScaffoldState.bottomSheetState.collapse()
                    }
                },
                onOrderSelected = { orderType ->
                    onEvent(
                        HomeScreenEvent.OnOrderSelected(
                            order = orderType,
                            pagingPokemonList = pokemonPagingItems.itemSnapshotList.items
                        )
                    )
                    coroutineScope.launch {
                        bottomSheetScaffoldState.bottomSheetState.collapse()
                    }
                }
            )
        },
        sheetPeekHeight = 0.dp
    ) { paddingValues ->
        HomeScreenContent(
            modifier = Modifier.padding(paddingValues),
            pokemonPagingItems = pokemonPagingItems,
            isSearchOrTypeOrOrderApplied = homeState.isSearchOrTypeOrOrderApplied,
            pokemonWithSearchAndTypeAndOrder = homeState.pokemonWithSearchAndTypeAndOrder,
            onExpandBottomSheetForSelectPokemonType = {
                isShowPokemonTypes.value = true
                coroutineScope.launch {
                    bottomSheetScaffoldState.bottomSheetState.expand()
                }
            },
            onExpandBottomSheetForOrderType = {
                isShowPokemonTypes.value = false
                coroutineScope.launch {
                    bottomSheetScaffoldState.bottomSheetState.expand()
                }
            },
            selectedPokemonType = homeState.selectedPokemonType,
            selectedOrder = homeState.selectedOrderType,
            onSearchTextChange = {
                onEvent(
                    HomeScreenEvent.OnSearchTextChange(
                        text = it,
                        pagingPokemonList = pokemonPagingItems.itemSnapshotList.items
                    )
                )
            },
            searchText = homeState.searchText,
            onFavoriteClick = {
                Log.d("HomeScreen", "onFavoriteClick: $it")
            }
        )
    }
}


@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    selectedPokemonType: PokemonType,
    pokemonPagingItems: LazyPagingItems<Pokemon>,
    selectedOrder: OrderType,
    searchText: String = "",
    isSearchOrTypeOrOrderApplied: Boolean = false,
    pokemonWithSearchAndTypeAndOrder: List<Pokemon>,
    onExpandBottomSheetForSelectPokemonType: () -> Unit,
    onExpandBottomSheetForOrderType: () -> Unit,
    onSearchTextChange: (String) -> Unit,
    onSearch: (String) -> Unit = { },
    onFavoriteClick: (Pokemon) -> Unit = { },
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = modifier.navigationBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            PoxedexTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                text = searchText,
                placeHolderText = stringResource(R.string.search_pokemon),
                onTextChange = onSearchTextChange,
                onSearch = onSearch
            )
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                SelectionType(
                    modifier = Modifier.weight(1f),
                    backgroundColor = selectedPokemonType.color,
                    textColor = selectedPokemonType.textColor,
                    name = selectedPokemonType.name,
                    onClick = onExpandBottomSheetForSelectPokemonType
                )

                SelectionType(
                    modifier = Modifier.weight(1f),
                    backgroundColor = Colors.AllTypes,
                    textColor = Colors.white,
                    name = selectedOrder.name,
                    onClick = onExpandBottomSheetForOrderType
                )
            }
        }

        if (!isSearchOrTypeOrOrderApplied) {
            items(
                count = pokemonPagingItems.itemCount,
                key = pokemonPagingItems.itemKey { it.id }
            ) { index ->
                pokemonPagingItems[index]?.let { pokemonItem ->
                    PokemonItem(
                        pokemon = pokemonItem,
                        onFavoriteClick = {
                            onFavoriteClick(pokemonItem)
                        }
                    )
                }
            }
        } else {
            items(pokemonWithSearchAndTypeAndOrder, key = { it.id }) {
                PokemonItem(
                    pokemon = it,
                    onFavoriteClick = {
                        onFavoriteClick(it)
                    }
                )
            }
        }
    }
}