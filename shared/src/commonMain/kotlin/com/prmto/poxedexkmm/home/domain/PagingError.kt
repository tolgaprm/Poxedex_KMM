package com.prmto.poxedexkmm.home.domain

enum class PagingError {
    SERVICE_UNAVAILABLE,
    ClIENT_ERROR,
    SERVER_ERROR,
    UNKNOWN_ERROR
}

class PagingException(val error: PagingError) : Exception(
    "An error occurred while translating: $error"
)