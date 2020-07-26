package com.raudonikis.movietracker.features.discover

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raudonikis.movietracker.extensions.io
import com.raudonikis.movietracker.model.MediaItem
import com.raudonikis.movietracker.model.MediaType
import com.raudonikis.movietracker.model.TimeWindow
import com.raudonikis.movietracker.navigation.NavigationHandler
import com.raudonikis.movietracker.repo.MediaRepository

class DiscoverViewModel @ViewModelInject constructor(
    private val mediaRepository: MediaRepository,
    private val navigationHandler: NavigationHandler
) :
    ViewModel() {

    val trendingMovies = MutableLiveData<List<MediaItem>>()
    val trendingTv = MutableLiveData<List<MediaItem>>()

    init {
        getTrendingMedia()
    }

    private fun getTrendingMedia() {
        viewModelScope.io {
            mediaRepository.getTrendingMedia(MediaType.MOVIE, TimeWindow.DAY)
                .onSuccess { mediaItems ->
                    trendingMovies.postValue(mediaItems)
                }
            mediaRepository.getTrendingMedia(MediaType.TV, TimeWindow.DAY)
                .onSuccess { mediaItems ->
                    trendingTv.postValue(mediaItems)
                }
        }
    }
}