package com.prmto.poxedexkmm.home.domain.model

data class Pokemon(
    val id: Long,
    val name: String,
    val type: List<Type>,
    val frontDefaultUrl: String,
)


