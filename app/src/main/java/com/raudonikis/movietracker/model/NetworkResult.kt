package com.raudonikis.movietracker.model

sealed class NetworkResult<out T> {

    data class Success<out T>(val data: T) : NetworkResult<T>()
    object SuccessEmpty : NetworkResult<Nothing>()
    data class Failure(val error: String, val message: String?) : NetworkResult<Nothing>()

    inline fun onSuccess(f: (data: T) -> Unit): NetworkResult<T> {
        return when (this) {
            is Success -> {
                f(data)
                this
            }
            else -> this
        }
    }

    inline fun onSuccessEmpty(f: () -> Unit): NetworkResult<T> {
        return when (this) {
            is SuccessEmpty -> {
                f()
                this
            }
            else -> this
        }
    }

    inline fun onFailure(f: (errorCode: String, message: String?) -> Unit): NetworkResult<T> {
        return when (this) {
            is Failure -> {
                f(error, message)
                this
            }
            else -> this
        }
    }

    companion object {
        fun <T> success(data: T) =
            Success(data)
        fun successEmpty() =
            SuccessEmpty
        fun failure(error: String, message: String = "") =
            Failure(
                error,
                message
            )
    }
}