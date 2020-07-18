package com.raudonikis.movietracker.features.search

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raudonikis.movietracker.extensions.io
import com.raudonikis.movietracker.model.MediaItem
import com.raudonikis.movietracker.repo.MediaRepository
import timber.log.Timber

class SearchViewModel @ViewModelInject constructor(
    private val mediaRepository: MediaRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    // Data
    val mediaList: MutableLiveData<List<MediaItem>> = MutableLiveData()
    var searchQuery = ""

    fun searchMedia() {
        viewModelScope.io {
            mediaRepository.searchMulti(searchQuery)
                .onSuccess { mediaList.postValue(it) }
                .onFailure { Timber.d("Failure -> $it") }
        }
    }

    companion object {
        private const val KEY_SEARCH_RESULTS = "search_results"
        private const val KEY_SEARCH_QUERY = "search_query"
    }
}