package com.raudonikis.movietracker.api

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class MoveInterceptor @Inject constructor() : Interceptor {

    private val API_KEY = "0fb342951813aa303f511d13be7eda20"

    override fun intercept(chain: Interceptor.Chain): Response {
        chain.request().let { request ->
            request.url().newBuilder().addQueryParameter("api_key", API_KEY).build().let { url ->
                request.newBuilder().url(url).build().run {
                    return chain.proceed(this)
                }
            }
        }
    }
}