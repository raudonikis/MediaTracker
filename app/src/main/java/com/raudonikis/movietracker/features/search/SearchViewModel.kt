package com.raudonikis.movietracker.features.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raudonikis.movietracker.extensions.io
import com.raudonikis.movietracker.repo.MoviesRepository
import timber.log.Timber

class SearchViewModel @ViewModelInject constructor(private val moviesRepository: MoviesRepository) :
    ViewModel() {

    fun searchMovies(query: String) {
        viewModelScope.io {
            moviesRepository.searchMulti(query)
                .onSuccess { Timber.d("Success -> ${it.totalResults}") }
                .onFailure { Timber.d("Failure -> $it") }
        }
    }
}