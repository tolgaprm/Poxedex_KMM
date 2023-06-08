package com.prmto.poxedexkmm.android.core.presentation

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.prmto.poxedexkmm.core.presentation.Colors

@Composable
fun MyApplicationTheme(
    content: @Composable () -> Unit
) {

    val colors = lightColors(
        primary = Color(Colors.EgyptianBlue),
        onPrimary = Color.White,
        background = Color.White,
        onSurface = Color(Colors.onSurfaceColor),
    )

    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(8.dp)
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
