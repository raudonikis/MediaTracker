package com.raudonikis.movietracker.ui.search

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.raudonikis.movietracker.data.util.MediaDatabaseMapper
import com.raudonikis.movietracker.extensions.io
import com.raudonikis.movietracker.domain.model.MediaItem
import com.raudonikis.movietracker.navigation.NavigationHandler
import com.raudonikis.movietracker.navigation.Router
import com.raudonikis.movietracker.data.repo.MediaRepository
import com.raudonikis.movietracker.util.Outcome
import kotlinx.coroutines.flow.map

class SearchViewModel @ViewModelInject constructor(
    private val mediaRepository: MediaRepository,
    private val navigationHandler: NavigationHandler,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    // Data
    // The media list returned by the search query
    val mediaItemList: MutableLiveData<Outcome<List<MediaItem>>> = MutableLiveData()

    // The currently selected media item from the search results
    val selectedMediaItemSearch: MutableLiveData<MediaItem> = MutableLiveData()

    // The currently selected media item, from the local database (null if it doesn't exist)
    val selectedMediaItemLocal: LiveData<MediaItem?> =
        selectedMediaItemSearch.switchMap { mediaItemSearch ->
            mediaRepository.getMedia(mediaItemSearch.id)
                .map { it?.let { MediaDatabaseMapper.mapFromMediaEntityToItem(it) } }
                .asLiveData(viewModelScope.coroutineContext)
        }
    var searchQuery = ""

    fun searchMedia() {
        if (searchQuery.isBlank()) return
        mediaItemList.postValue(Outcome.loading())
        viewModelScope.io {
            mediaRepository.searchMulti(searchQuery).let {
                mediaItemList.postValue(it)
            }
        }
    }

    fun addMediaItemToWatchedList() {
        viewModelScope.io {
            selectedMediaItemSearch.value?.let {
                mediaRepository.addToWatchedList(it)
            }
        }
    }

    fun onMediaItemSelected(item: MediaItem) {
        selectedMediaItemSearch.postValue(item)
        navigationHandler.navigate(Router.searchFragmentToDetailsRemoteFragment)
    }
}