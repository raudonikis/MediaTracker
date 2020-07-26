package com.raudonikis.movietracker.features.tvshows

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.raudonikis.movietracker.model.Movie
import com.raudonikis.movietracker.model.TvShow
import com.raudonikis.movietracker.navigation.NavigationHandler
import com.raudonikis.movietracker.repo.MediaRepository
import kotlinx.coroutines.flow.map

class TvShowsViewModel @ViewModelInject constructor(
    private val mediaRepository: MediaRepository,
    private val navigationHandler: NavigationHandler,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    // Data
    private val _searchQuery = MutableLiveData<String>("")
    val searchQuery: LiveData<String> = _searchQuery
    private val tvShows = mediaRepository.getAllTvShows()
    val filteredTvShows = _searchQuery.switchMap { query ->
        when {
            query.isBlank() -> tvShows.asLiveData(viewModelScope.coroutineContext)
            else -> tvShows.map { tvShowsList ->
                tvShowsList.filter { it.name?.contains(query, ignoreCase = true) ?: false }
            }.asLiveData(viewModelScope.coroutineContext)
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.postValue(query)
    }

    fun onTvShowSelected(tvShow: TvShow) {

    }
}