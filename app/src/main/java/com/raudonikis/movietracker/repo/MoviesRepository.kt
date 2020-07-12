package com.raudonikis.movietracker.repo

import com.raudonikis.movietracker.api.MovieApiService
import com.raudonikis.movietracker.api.safeApiCall
import com.raudonikis.movietracker.model.MovieItem
import com.raudonikis.movietracker.model.Outcome
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val movieApiService: MovieApiService) {

    suspend fun searchMulti(query: String): Outcome<List<MovieItem>> {
        return safeApiCall("searchMulti") { movieApiService.searchMulti(query) }
            .map { multiResponse ->
                multiResponse.results?.map { result ->
                    MovieItem(result.title, result.posterPath)
                } ?: emptyList()
            }
    }
}