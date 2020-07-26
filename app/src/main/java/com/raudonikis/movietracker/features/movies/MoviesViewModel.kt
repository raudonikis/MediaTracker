package com.raudonikis.movietracker.features.movies

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.raudonikis.movietracker.model.Movie
import com.raudonikis.movietracker.navigation.NavigationHandler
import com.raudonikis.movietracker.repo.MediaRepository
import kotlinx.coroutines.flow.map

class MoviesViewModel @ViewModelInject constructor(
    private val mediaRepository: MediaRepository,
    private val navigationHandler: NavigationHandler,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    // Data
    private val _searchQuery = MutableLiveData<String>("")
    val searchQuery: LiveData<String> = _searchQuery
    private val movies = mediaRepository.getAllMovies()
    val filteredMovies = _searchQuery.switchMap { query ->
        when {
            query.isBlank() -> movies.asLiveData(viewModelScope.coroutineContext)
            else -> movies.map { movieList ->
                movieList.filter { it.title?.contains(query, ignoreCase = true) ?: false }
            }.asLiveData(viewModelScope.coroutineContext)
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.postValue(query)
    }

    fun onMovieSelected(movie: Movie) {

    }
}