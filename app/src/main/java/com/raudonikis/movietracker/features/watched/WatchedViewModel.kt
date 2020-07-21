package com.raudonikis.movietracker.features.watched

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.raudonikis.movietracker.database.util.MediaDatabaseMapper
import com.raudonikis.movietracker.extensions.io
import com.raudonikis.movietracker.model.MediaItem
import com.raudonikis.movietracker.model.MediaItemAdapter
import com.raudonikis.movietracker.navigation.NavigationHandler
import com.raudonikis.movietracker.navigation.Router
import com.raudonikis.movietracker.repo.MediaRepository
import kotlinx.coroutines.flow.map

class WatchedViewModel @ViewModelInject constructor(
    private val mediaRepository: MediaRepository,
    private val navigationHandler: NavigationHandler
) : ViewModel(), MediaItemAdapter.Interaction {

    val media = mediaRepository.getAllMedia()
        .map { mediaList -> mediaList.map { MediaDatabaseMapper.mapFromMediaEntityToItem(it) } }
        .asLiveData(viewModelScope.coroutineContext)

    val selectedMedia: MutableLiveData<MediaItem> = MutableLiveData()

    fun removeFromWatchedList() {
        viewModelScope.io {
            selectedMedia.value?.let {
                mediaRepository.removeFromWatchedList(it)
                navigationHandler.navigateBack()
            }
        }
    }

    override fun onMediaItemSelected(position: Int, item: MediaItem) {
        selectedMedia.postValue(item)
        navigationHandler.navigate(Router.watchedFragmentToDetailsLocalFragment)
    }
}