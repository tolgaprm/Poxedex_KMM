package com.prmto.poxedexkmm.android.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prmto.poxedexkmm.android.R
import com.prmto.poxedexkmm.core.data.OrderType
import com.prmto.poxedexkmm.core.data.PokemonType
import com.prmto.poxedexkmm.core.presentation.Colors


@Composable
fun SheetContent(
    allPokemonTypes: List<PokemonType>,
    isShowPokemonTypes: Boolean = true,
    onPokemonTypeSelected: (PokemonType) -> Unit,
    onOrderSelected: (OrderType) -> Unit
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
            OrderType.values().forEach { order ->
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