package com.raudonikis.movietracker.api

import com.raudonikis.movietracker.model.response.SearchResponse
import com.raudonikis.movietracker.model.response.MultiSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("search/multi")
    suspend fun searchMulti(@Query("query") query: String) : Response<MultiSearchResponse>

    @GET("search/movie")
    suspend fun searchMovies(@Query("query") query: String) : Response<SearchResponse>
}