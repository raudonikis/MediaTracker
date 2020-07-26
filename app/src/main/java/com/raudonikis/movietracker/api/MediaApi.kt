package com.raudonikis.movietracker.api

import com.raudonikis.movietracker.api.response.MediaResultListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MediaApi {

    @GET("search/multi")
    suspend fun searchMulti(@Query("query") query: String): Response<MediaResultListResponse>

    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrendingMedia(
        @Path("media_type") mediaType: String,
        @Path("time_window") timeWindow: String
    ): Response<MediaResultListResponse>
}