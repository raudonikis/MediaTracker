package com.raudonikis.movietracker.api

import com.raudonikis.movietracker.constants.MovieApiConstants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class MoveInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        chain.request().let { request ->
            request.url().newBuilder().addQueryParameter("api_key", MovieApiConstants.API_KEY).build().let { url ->
                request.newBuilder().url(url).build().run {
                    return chain.proceed(this)
                }
            }
        }
    }
}