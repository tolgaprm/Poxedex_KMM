package com.prmto.poxedexkmm.home.domain.model

import androidx.annotation.DrawableRes

actual data class Type(
    @DrawableRes val typeImageRes: Int,
    actual val id: Int,
    actual val name: String,
    actual val color: Long,
    actual val textColor: Long
)