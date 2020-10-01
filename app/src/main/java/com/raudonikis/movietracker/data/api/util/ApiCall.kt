package com.raudonikis.movietracker.data.api.util

import com.raudonikis.movietracker.domain.model.Error
import com.raudonikis.movietracker.util.Outcome
import retrofit2.Response
import timber.log.Timber

suspend fun <T> apiCall(call: suspend () -> Response<T>): Outcome<T> {

    return apiResult(call)
        .onSuccess { data ->
            Timber.d("SafeApiCall success -> result: $data")
        }
        .onFailure { errorCode ->
            Timber.d("SafeApiCall failure -> result: $errorCode")
        }
        .onSuccessEmpty {
            Timber.d("SafeApiCall successEmpty")
        }
}

// TODO return more specific errors
private suspend fun <T> apiResult(call: suspend () -> Response<T>): Outcome<T> {
    return try {
        val response = call.invoke()
        val data = response.body()
        when {
            response.isSuccessful -> {
                when {
                    data != null -> Outcome.success(
                        data
                    )
                    else -> Outcome.successEmpty()
                }
            }
            else -> {
                when {
                    response.errorBody() != null -> Outcome.failure(
                        Error.GENERIC,
                        response.errorBody().toString()
                    )
                    else -> Outcome.failure(
                        Error.GENERIC,
                        response.code().toString()
                    )
                }
            }
        }
    } catch (e: Exception) {
        Timber.d("SafeApiCall exception thrown")
        Outcome.failure(
            Error.GENERIC,
            e.message.toString()
        )
    }
}