package com.raudonikis.movietracker.api

import com.raudonikis.movietracker.model.Error
import com.raudonikis.movietracker.model.Outcome
import retrofit2.Response
import timber.log.Timber
import java.net.HttpURLConnection

suspend fun <T> safeApiCall(method: String = "", call: suspend () -> Response<T>): Outcome<T> {

    return safeApiResult(call)
        .onSuccess { data ->
            Timber.d("SafeApiCall success -> Method:$method, result: $data")
        }
        .onFailure { errorCode ->
            Timber.d("SafeApiCall failure -> Method:$method, result: $errorCode")
        }
}

private suspend fun <T> safeApiResult(call: suspend () -> Response<T>): Outcome<T> {
    return try {
        val response = call.invoke()
        val data = response.body()
        when {
            response.isSuccessful && data != null -> Outcome.success(data)
            response.isSuccessful && response.code() == HttpURLConnection.HTTP_NO_CONTENT -> Outcome.successEmpty()
            !response.isSuccessful && response.errorBody() != null -> {
                Outcome.failure(Error.GENERIC, response.errorBody().toString())
            }
            else -> Outcome.failure(Error.GENERIC, response.code().toString())
        }
    } catch (e: Exception) {
        Timber.d("SafeApiCall exception thrown")
        Outcome.failure(Error.GENERIC, e.message.toString())
    }
}