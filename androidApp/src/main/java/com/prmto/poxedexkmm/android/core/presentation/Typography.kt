package com.prmto.poxedexkmm.android.core.presentation

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.prmto.poxedexkmm.android.R

val FontFamily.Companion.Poppins
    get() = FontFamily(
        Font(R.font.poppins, weight = FontWeight.Normal),
        Font(R.font.poppins_medium, weight = FontWeight.Medium),
        Font(R.font.poppins_semibold, weight = FontWeight.SemiBold),
    )


val typography = Typography(
    defaultFontFamily = FontFamily.Poppins,
    h4 = TextStyle(
        fontFamily = FontFamily.Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 26.sp
    ),
    h5 = TextStyle(
        fontFamily = FontFamily.Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 21.sp
    ),
    h3 = TextStyle(
        fontFamily = FontFamily.Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 32.sp
    ),
    button = TextStyle(
        fontFamily = FontFamily.Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    ),
    body1 = TextStyle(
        fontFamily = FontFamily.Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    body2 = TextStyle(
        fontFamily = FontFamily.Poppins,
        fontWeight = FontWeight.Thin,
        fontSize = 14.sp
    )

)