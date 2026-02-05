package com.flavorfusion.common_domain.model.error

sealed interface DataError : Error {
    sealed interface Network : DataError {
        data object RequestTimeout : Network
        data object ServerError : Network
        data object NoInternet : Network
        data object NoPing : Network
        data class CustomServerError(val message: String) : Network
    }

    sealed interface Local : DataError {
        data object IOError : Local
    }

    data object Unknown : DataError
}