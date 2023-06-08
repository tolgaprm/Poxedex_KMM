package com.prmto.poxedexkmm.android.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SelectionType(
    modifier: Modifier = Modifier,
    backgroundColor: Long,
    textColor: Long,
    name: String,
    onClick: () -> Unit = { }
) {
    Box(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(color = Color(backgroundColor))
            .clickable { onClick() }
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .padding(end = 16.dp)
                .align(Alignment.Center),
            text = name,
            textAlign = TextAlign.Center,
            color = Color(textColor),
            style = MaterialTheme.typography.button.copy(fontSize = 12.sp),
        )

        Icon(
            modifier = Modifier
                .padding(end = 8.dp)
                .align(Alignment.CenterEnd),
            imageVector = Icons.Default.KeyboardArrowDown,
            tint = Color(textColor),
            contentDescription = null,
        )
    }
}
