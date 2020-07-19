package com.raudonikis.movietracker.repo

import androidx.lifecycle.LiveData
import com.raudonikis.movietracker.api.MediaApi
import com.raudonikis.movietracker.api.MediaApiMapper
import com.raudonikis.movietracker.database.MediaDatabaseMapper
import com.raudonikis.movietracker.database.daos.MovieDao
import com.raudonikis.movietracker.database.daos.TvSeriesDao
import com.raudonikis.movietracker.database.entities.MovieEntity
import com.raudonikis.movietracker.database.entities.TvSeriesEntity
import com.raudonikis.movietracker.model.MediaItem
import com.raudonikis.movietracker.model.MediaType
import com.raudonikis.movietracker.util.Outcome
import com.raudonikis.movietracker.util.apiCall
import javax.inject.Inject

class MediaRepository @Inject constructor(
    private val mediaApi: MediaApi,
    private val movieDao: MovieDao,
    private val tvSeriesDao: TvSeriesDao
) {

    suspend fun searchMulti(query: String): Outcome<List<MediaItem>> {
        return apiCall { mediaApi.searchMulti(query) }
            .map { multiResponse ->
                MediaApiMapper.mapFromMultiSearchResponse(multiResponse)
            }
    }

    suspend fun addToWatched(media: MediaItem) {
        when (media.mediaType) {
            MediaType.MOVIE -> {
                MediaDatabaseMapper.mapFromMediaItemToMovie(media).let {
                    movieDao.insertMovie(it)
                }
            }
            MediaType.TV -> {
                MediaDatabaseMapper.mapFromMediaItemToTvSeries(media).let {
                    tvSeriesDao.insertTvSeries(it)
                }
            }
            else -> {
            }
        }
    }

    suspend fun getMovies(): List<MovieEntity> {
        return movieDao.getMovies()
    }

    fun getTvSeries(): LiveData<List<TvSeriesEntity>> {
        return tvSeriesDao.getTvSeries()
    }
}