package com.den.gorobets.getmovie.utils

sealed class ResponseEvents<T> {

    data class Success<T>(val result: T?) : ResponseEvents<T>()
    data class Failure<T>(val error: String) : ResponseEvents<T>()
    class Loading<T> : ResponseEvents<T>()
}