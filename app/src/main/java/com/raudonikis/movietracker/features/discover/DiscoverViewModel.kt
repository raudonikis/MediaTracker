package com.raudonikis.movietracker.features.discover

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
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
) : ViewModel() {

    // Data
    private val _trendingMovies = MutableLiveData<List<MediaItem>>()
    val trendingMovies: LiveData<List<MediaItem>> = _trendingMovies
    private val _trendingTv = MutableLiveData<List<MediaItem>>()
    val trendingTv: LiveData<List<MediaItem>> = _trendingTv

    init {
        getTrendingMedia()
    }

    private fun getTrendingMedia() {
        viewModelScope.io {
            mediaRepository.getTrendingMedia(MediaType.MOVIE, TimeWindow.DAY)
                .onSuccess { movies ->
                    _trendingMovies.postValue(movies)
                }
            mediaRepository.getTrendingMedia(MediaType.TV, TimeWindow.DAY)
                .onSuccess { tvSeries ->
                    _trendingTv.postValue(tvSeries)
                }
        }
    }
}