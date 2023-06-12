package com.prmto.poxedexkmm.android.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.prmto.poxedexkmm.home.domain.model.Pokemon
import com.prmto.poxedexkmm.home.domain.model.Type

@Composable
fun PokemonItem(
    pokemon: Pokemon,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(Color(pokemon.type.first().color).copy(alpha = 0.2f))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .height(120.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            PokemonId(id = pokemon.id.toInt())

            Text(
                text = pokemon.name.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.h5
            )

            Row {
                pokemon.type.forEach {
                    TypeItem(type = it)
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }

        PokemonCharacterItem(pokemon = pokemon)
    }
}

@Composable
fun PokemonId(id: Int) {
    Text(
        text = "Nº${id.toString().padStart(3, '0')}",
        style = MaterialTheme.typography.body2.copy(
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        ),
    )
}


@Composable
fun TypeItem(
    type: Type,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color(type.color))
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(20.dp)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.padding(4.dp),
                painter = painterResource(id = type.typeImageRes),
                contentDescription = type.name,
                tint = Color(type.color)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            modifier = Modifier.alignByBaseline(),
            text = type.name.replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.h4.copy(fontSize = 14.sp),
            color = Color(type.textColor)
        )
    }
}

@Composable
fun PokemonCharacterItem(
    pokemon: Pokemon
) {
    val firstType = pokemon.type.first()
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .background(Color(firstType.color)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(120.dp)
                .padding(horizontal = 16.dp, vertical = 4.dp),
            painter = painterResource(id = firstType.typeImageRes),
            contentDescription = null,
            tint = Color.White.copy(alpha = 0.5f)
        )

        AsyncImage(
            modifier = Modifier
                .size(120.dp)
                .padding(horizontal = 16.dp, vertical = 4.dp),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(LocalContext.current)
                .data(pokemon.frontDefaultUrl)
                .build(),
            contentDescription = pokemon.name
        )

    }
}
