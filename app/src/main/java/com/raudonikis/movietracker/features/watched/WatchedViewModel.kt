package com.raudonikis.movietracker.features.watched

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.raudonikis.movietracker.model.MediaItem
import com.raudonikis.movietracker.model.MediaItemAdapter
import com.raudonikis.movietracker.repo.MediaRepository

class WatchedViewModel @ViewModelInject constructor(private val mediaRepository: MediaRepository) :
    ViewModel(), MediaItemAdapter.Interaction {

    val media = mediaRepository.getMedia()

    override fun onMediaItemSelected(position: Int, item: MediaItem) {
        // nothing yet
    }
}