package com.raudonikis.movietracker.api

import com.raudonikis.movietracker.api.response.MultiSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MediaApi {

    @GET("search/multi")
    suspend fun searchMulti(@Query("query") query: String): Response<MultiSearchResponse>
}