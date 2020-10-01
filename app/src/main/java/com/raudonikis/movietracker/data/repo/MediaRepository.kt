package com.raudonikis.movietracker.data.repo

import com.raudonikis.movietracker.data.daos.MediaDao
import com.raudonikis.movietracker.data.entities.MediaEntity
import com.raudonikis.movietracker.data.util.MediaDatabaseMapper
import com.raudonikis.movietracker.domain.usecases.discover.PopularMediaUseCase
import com.raudonikis.movietracker.domain.usecases.discover.TrendingMediaUseCase
import com.raudonikis.movietracker.domain.usecases.search.SearchMediaUseCase
import com.raudonikis.movietracker.domain.model.MediaItem
import com.raudonikis.movietracker.domain.model.Movie
import com.raudonikis.movietracker.domain.model.TimeWindow
import com.raudonikis.movietracker.domain.model.TvShow
import com.raudonikis.movietracker.util.Outcome
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MediaRepository @Inject constructor(
    private val mediaDao: MediaDao,
    private val trendingMediaUseCase: TrendingMediaUseCase,
    private val popularMediaUseCase: PopularMediaUseCase,
    private val searchMediaUseCase: SearchMediaUseCase
) {

    suspend fun searchMulti(query: String): Outcome<List<MediaItem>> =
        searchMediaUseCase.searchMulti(query)

    suspend fun getPopularMovies(): Outcome<List<MediaItem>> =
        popularMediaUseCase.getPopularMovies()

    suspend fun getPopularTvSeries(): Outcome<List<MediaItem>> =
        popularMediaUseCase.getPopularTvSeries()

    suspend fun getTrendingTvSeries(timeWindow: TimeWindow = TimeWindow.DAY): Outcome<List<MediaItem>> =
        trendingMediaUseCase.getTrendingTvSeries(timeWindow)

    suspend fun getTrendingMovies(timeWindow: TimeWindow = TimeWindow.DAY): Outcome<List<MediaItem>> =
        trendingMediaUseCase.getTrendingMovies(timeWindow)

    suspend fun addToWatchedList(media: MediaItem) {
        MediaDatabaseMapper.mapFromMediaItemToEntity(media).let {
            mediaDao.insertMedia(it)
        }
    }

    suspend fun removeFromWatchedList(media: MediaItem) {
        MediaDatabaseMapper.mapFromMediaItemToEntity(media).let {
            mediaDao.remove(it)
        }
    }

    fun getAllMedia(): Flow<List<MediaEntity>> = mediaDao.getAllMedia()

    fun getAllMovies(): Flow<List<Movie>> {
        return mediaDao.getAllMovies().map { mediaList ->
            mediaList.map { MediaDatabaseMapper.mapFromMediaEntityToMovie(it) }
        }
    }

    fun getAllTvShows(): Flow<List<TvShow>> {
        return mediaDao.getAllTvShows().map { mediaList ->
            mediaList.map { MediaDatabaseMapper.mapFromMediaEntityToTvShow(it) }
        }
    }

    fun getMedia(id: Int): Flow<MediaEntity?> = mediaDao.getMedia(id)
}