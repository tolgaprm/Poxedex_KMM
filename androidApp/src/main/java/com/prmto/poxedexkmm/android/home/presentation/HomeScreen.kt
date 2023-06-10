package com.prmto.poxedexkmm.android.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prmto.poxedexkmm.android.R
import com.prmto.poxedexkmm.android.home.presentation.components.PoxedexTextField
import com.prmto.poxedexkmm.android.home.presentation.components.SelectionType
import com.prmto.poxedexkmm.core.data.Order
import com.prmto.poxedexkmm.core.data.PokemonType
import com.prmto.poxedexkmm.core.data.order
import com.prmto.poxedexkmm.core.presentation.Colors
import com.prmto.poxedexkmm.home.presentation.HomeScreenEvent
import com.prmto.poxedexkmm.home.presentation.HomeScreenState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    homeState: HomeScreenState,
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
                onPokemonTypeSelected = {
                    onEvent(HomeScreenEvent.OnPokemonTypeSelected(it))
                    coroutineScope.launch {
                        bottomSheetScaffoldState.bottomSheetState.collapse()
                    }
                },
                onOrderSelected = {
                    onEvent(HomeScreenEvent.OnOrderSelected(it))
                    coroutineScope.launch {
                        bottomSheetScaffoldState.bottomSheetState.collapse()
                    }
                }
            )
        },
        sheetPeekHeight = 0.dp
    ) { paddingValues ->
        HomeScreenContent(
            modifier = Modifier
                .navigationBarsPadding()
                .padding(paddingValues)
                .padding(16.dp),
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
            onSearchTextChange = { onEvent(HomeScreenEvent.OnSearchTextChange(it)) },
            searchText = homeState.searchText,
        )
    }
}


@Composable
fun SheetContent(
    allPokemonTypes: List<PokemonType>,
    isShowPokemonTypes: Boolean = true,
    onPokemonTypeSelected: (PokemonType) -> Unit,
    onOrderSelected: (Order) -> Unit
) {
    val titleText = if (isShowPokemonTypes) {
        stringResource(R.string.choose_type)
    } else {
        stringResource(R.string.select_the_order)
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .width(38.dp)
                .height(3.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(Color.Gray)
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = titleText,
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (isShowPokemonTypes) {
            allPokemonTypes.forEach { pokemonType ->
                BottomSheetItem(
                    name = pokemonType.name,
                    backgroundColorLong = pokemonType.color,
                    textColorLong = pokemonType.textColor,
                    onClick = {
                        onPokemonTypeSelected(pokemonType)
                    }
                )
            }
        } else {
            order.forEach { order ->
                BottomSheetItem(
                    name = order.name,
                    backgroundColorLong = Colors.AllTypes,
                    textColorLong = Colors.white,
                    onClick = {
                        onOrderSelected(order)
                    }
                )
            }
        }

    }
}

@Composable
fun BottomSheetItem(
    modifier: Modifier = Modifier,
    backgroundColorLong: Long,
    textColorLong: Long,
    name: String,
    onClick: () -> Unit = { }
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(color = Color(backgroundColorLong))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = name,
            textAlign = TextAlign.Center,
            color = Color(textColorLong),
            style = MaterialTheme.typography.button.copy(fontSize = 14.sp),
        )
    }
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    selectedPokemonType: PokemonType,
    selectedOrder: Order,
    searchText: String = "",
    onExpandBottomSheetForSelectPokemonType: () -> Unit,
    onExpandBottomSheetForOrderType: () -> Unit,
    onSearchTextChange: (String) -> Unit,
    onSearch: (String) -> Unit = { }
) {
    Column(
        modifier = modifier
    ) {
        PoxedexTextField(
            modifier = Modifier
                .fillMaxWidth(),
            text = searchText,
            placeHolderText = stringResource(R.string.search_pokemon),
            onTextChange = onSearchTextChange,
            onSearch = onSearch
        )
        Spacer(modifier = Modifier.height(16.dp))
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
}