package com.flavorfusion.common_domain.model

import com.flavorfusion.common_domain.model.error.Error

typealias RootError = Error

sealed interface Result<out T> {
    data class Success<out T>(val data: T) : Result<T>
    data class Error<out T>(val error: RootError) : Result<T>
}

inline fun <T> Result<T>.onSuccess(action: (T) -> Unit): Result<T> {
    if (this is Result.Success) {
        action(data)
    }
    return this
}

inline fun <T> Result<T>.onError(action: (RootError) -> Unit): Result<T> {
    if (this is Result.Error) {
        action(error)
    }
    return this
}