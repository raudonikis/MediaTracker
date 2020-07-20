package com.raudonikis.movietracker.features.watched

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.raudonikis.movietracker.database.util.MediaDatabaseMapper
import com.raudonikis.movietracker.model.MediaItem
import com.raudonikis.movietracker.model.MediaItemAdapter
import com.raudonikis.movietracker.repo.MediaRepository
import kotlinx.coroutines.flow.map

class WatchedViewModel @ViewModelInject constructor(private val mediaRepository: MediaRepository) :
    ViewModel(), MediaItemAdapter.Interaction {

    val media = mediaRepository.getAllMedia()
        .map { mediaList -> mediaList.map { MediaDatabaseMapper.mapFromMediaEntityToItem(it) } }
        .asLiveData(viewModelScope.coroutineContext)

    override fun onMediaItemSelected(position: Int, item: MediaItem) {
        // nothing yet
    }
}