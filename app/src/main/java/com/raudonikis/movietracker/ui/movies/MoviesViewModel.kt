package com.raudonikis.movietracker.ui.movies

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.raudonikis.movietracker.domain.model.Movie
import com.raudonikis.movietracker.navigation.NavigationHandler
import com.raudonikis.movietracker.navigation.Router
import com.raudonikis.movietracker.data.repo.MediaRepository
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
        navigationHandler.navigate(Router.moviesFragmentToMovieDetailsFragment(movie))
    }
}