package com.raudonikis.movietracker.features.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raudonikis.movietracker.api.MovieApiService
import com.raudonikis.movietracker.extensions.io
import timber.log.Timber

class SearchViewModel @ViewModelInject constructor(private val movieApiService: MovieApiService) :
    ViewModel() {

    fun searchMovies(query: String) {
        viewModelScope.io {
            val result = movieApiService.searchMovies(query)
            Timber.d("result body -> ${result.body()}")
        }
    }
}