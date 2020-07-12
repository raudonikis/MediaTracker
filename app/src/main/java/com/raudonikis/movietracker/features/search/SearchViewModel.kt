package com.raudonikis.movietracker.features.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raudonikis.movietracker.extensions.io
import com.raudonikis.movietracker.model.MovieItem
import com.raudonikis.movietracker.repo.MoviesRepository
import timber.log.Timber

class SearchViewModel @ViewModelInject constructor(private val moviesRepository: MoviesRepository) :
    ViewModel() {

    val movies = MutableLiveData<List<MovieItem>>()

    fun searchMovies(query: String) {
        viewModelScope.io {
            moviesRepository.searchMulti(query)
                .onSuccess { movies.postValue(it) }
                .onFailure { Timber.d("Failure -> $it") }
        }
    }
}