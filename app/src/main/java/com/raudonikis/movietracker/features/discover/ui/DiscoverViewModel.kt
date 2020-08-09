package com.raudonikis.movietracker.features.discover.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raudonikis.movietracker.extensions.io
import com.raudonikis.movietracker.model.MediaItem
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
    private val _popularMovies = MutableLiveData<List<MediaItem>>()
    val popularMovies: LiveData<List<MediaItem>> = _popularMovies
    private val _popularTv = MutableLiveData<List<MediaItem>>()
    val popularTv: LiveData<List<MediaItem>> = _popularTv

    init {
        getTrendingMedia()
        getPopularMedia()
    }

    private fun getPopularMedia() {
        viewModelScope.io {
            mediaRepository.getPopularMovies()
                .onSuccess { movies ->
                    _popularMovies.postValue(movies)
                }
            mediaRepository.getPopularTvSeries()
                .onSuccess { tvSeries ->
                    _popularTv.postValue(tvSeries)
                }
        }
    }

    private fun getTrendingMedia() {
        viewModelScope.io {
            mediaRepository.getTrendingMovies()
                .onSuccess { movies ->
                    _trendingMovies.postValue(movies)
                }
            mediaRepository.getTrendingTvSeries()
                .onSuccess { tvSeries ->
                    _trendingTv.postValue(tvSeries)
                }
        }
    }
}