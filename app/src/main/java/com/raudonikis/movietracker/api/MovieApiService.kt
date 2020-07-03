package com.raudonikis.movietracker.api

import com.raudonikis.movietracker.model.response.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    /*@GET("search/multi")
    suspend fun searchMulti(@Query("query") query: String) : Response<String>*/

    @GET("search/movie")
    suspend fun searchMovies(@Query("query") query: String) : Response<SearchResponse>
}