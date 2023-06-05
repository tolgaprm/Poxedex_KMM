package com.prmto.poxedexkmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform