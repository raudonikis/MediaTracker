package com.raudonikis.movietracker.repo

import com.raudonikis.movietracker.api.MovieApiService
import com.raudonikis.movietracker.api.safeApiCall
import com.raudonikis.movietracker.model.Outcome
import com.raudonikis.movietracker.model.response.MultiSearchResponse
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val movieApiService: MovieApiService) {

    suspend fun searchMulti(query: String): Outcome<MultiSearchResponse> {
        return safeApiCall("searchMulti") { movieApiService.searchMulti(query) }
    }
}