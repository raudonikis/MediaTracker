package com.raudonikis.movietracker.features.mediadetails

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raudonikis.movietracker.extensions.io
import com.raudonikis.movietracker.model.MediaItem
import com.raudonikis.movietracker.repo.MediaRepository


class MediaDetailsViewModel @ViewModelInject constructor(
    private val mediaRepository: MediaRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    fun addToWatched(media: MediaItem) {
        viewModelScope.io {
            mediaRepository.addToWatched(media)
        }
    }
}