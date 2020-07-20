package com.raudonikis.movietracker.util

import com.raudonikis.movietracker.model.Error
import timber.log.Timber

sealed class Outcome<out T> {

    data class Success<out T>(val data: T) : Outcome<T>()
    object SuccessEmpty : Outcome<Nothing>()
    object Loading : Outcome<Nothing>()
    data class Failure(val error: Error, val message: String?) : Outcome<Nothing>()

    inline fun onSuccess(f: (data: T) -> Unit): Outcome<T> {
        return when (this) {
            is Success -> {
                f(data)
                this
            }
            else -> this
        }
    }

    inline fun onSuccessEmpty(f: () -> Unit): Outcome<T> {
        return when (this) {
            is SuccessEmpty -> {
                f()
                this
            }
            else -> this
        }
    }

    inline fun onFailure(f: (error: Error) -> Unit): Outcome<T> {
        return when (this) {
            is Failure -> {
                f(error)
                Timber.d("Error occurred, code -> $error, message -> $message")
                this
            }
            else -> this
        }
    }

    inline fun onLoading(f: () -> Unit): Outcome<T> {
        return when (this) {
            is Loading -> {
                f()
                this
            }
            else -> this
        }
    }

    inline fun <C> map(transform: (T) -> C): Outcome<C> =
        when (this) {
            is Success -> success(
                transform(data)
            )
            is Failure -> failure(
                error
            )
            is SuccessEmpty -> successEmpty()
            is Loading -> loading()
        }

    companion object {
        fun <T> success(data: T) =
            Success(data)

        fun successEmpty() =
            SuccessEmpty

        fun loading() = Loading
        fun failure(error: Error, message: String = "") =
            Failure(
                error,
                message
            )
    }
}