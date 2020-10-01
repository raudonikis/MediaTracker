package com.raudonikis.movietracker.data.api.util

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class MediaInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        chain.request().let { request ->
            request.url().newBuilder().addQueryParameter(
                QUERY_API_KEY,
                MediaApiConstants.API_KEY
            )
                .build().let { url ->
                request.newBuilder().url(url).build().run {
                    return chain.proceed(this)
                }
            }
        }
    }

    companion object {
        private const val QUERY_API_KEY = "api_key"
    }
}