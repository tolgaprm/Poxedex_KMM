package com.prmto.poxedexkmm.home.domain.model

actual data class Type(
    val typeImage: String,
    actual val id: Int,
    actual val name: String,
    actual val color: Long,
    actual val textColor: Long
)
