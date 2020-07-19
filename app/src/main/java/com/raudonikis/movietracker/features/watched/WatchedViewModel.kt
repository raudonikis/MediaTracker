package com.raudonikis.movietracker.features.watched

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.raudonikis.movietracker.database.MediaDatabaseMapper
import com.raudonikis.movietracker.database.entities.MovieEntity
import com.raudonikis.movietracker.model.MediaItem
import com.raudonikis.movietracker.model.MediaItemAdapter
import com.raudonikis.movietracker.repo.MediaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WatchedViewModel @ViewModelInject constructor(private val mediaRepository: MediaRepository) :
    ViewModel(), MediaItemAdapter.Interaction {

    val movies: LiveData<List<MediaItem>> = liveData {
        emit(getMovies().map { MediaDatabaseMapper.mapFromMovieToMediaItem(it) })
    }
    val tvSeries = mediaRepository.getTvSeries()

    private suspend fun getMovies(): List<MovieEntity> =
        withContext(Dispatchers.IO) {
            mediaRepository.getMovies()
        }

    override fun onMediaItemSelected(position: Int, item: MediaItem) {
        // nothing yet
    }
}