package com.prmto.poxedexkmm.android.home.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prmto.poxedexkmm.android.core.presentation.MyApplicationTheme

@Composable
fun PoxedexTextField(
    modifier: Modifier = Modifier,
    text: String,
    placeHolderText: String,
    onTextChange: (String) -> Unit,
    onSearch: (String) -> Unit = {},
) {
    TextField(
        modifier = modifier
            .clip(RoundedCornerShape(30.dp))
            .border(
                width = 1.5.dp,
                color = MaterialTheme.colors.onSurface,
                shape = RoundedCornerShape(30.dp)
            ),
        value = text,
        onValueChange = onTextChange,
        placeholder = {
            Text(
                text = placeHolderText,
                style = MaterialTheme.typography.body1
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = "Search"
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface
        ),
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = androidx.compose.ui.text.input.ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(text)
            }
        )
    )
}


@Preview
@Composable
fun PoxedexTextFieldPreview() {
    MyApplicationTheme {
        PoxedexTextField(
            text = "Bulbasaur",
            onTextChange = {},
            placeHolderText = "Search"
        )
    }
}