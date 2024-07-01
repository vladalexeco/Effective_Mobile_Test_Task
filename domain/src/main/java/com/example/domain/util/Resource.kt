package com.example.domain.util

sealed class Resource<T>(val data: T? = null, val networkError: NetworkError? = null) {
    class Success<T>(data: T): Resource<T>(data)
    class Error<T>(networkError: NetworkError, data: T? = null): Resource<T>(data, networkError)
}

enum class NetworkError {
    BAD_CONNECTION, SERVER_ERROR
}