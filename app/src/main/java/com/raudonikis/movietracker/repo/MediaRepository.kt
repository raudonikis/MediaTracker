package com.raudonikis.movietracker.repo

import com.raudonikis.movietracker.api.MediaApi
import com.raudonikis.movietracker.api.util.MediaApiMapper
import com.raudonikis.movietracker.database.daos.MediaDao
import com.raudonikis.movietracker.database.entities.MediaEntity
import com.raudonikis.movietracker.database.util.MediaDatabaseMapper
import com.raudonikis.movietracker.model.MediaItem
import com.raudonikis.movietracker.model.MediaType
import com.raudonikis.movietracker.model.TimeWindow
import com.raudonikis.movietracker.util.Outcome
import com.raudonikis.movietracker.util.apiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MediaRepository @Inject constructor(
    private val mediaApi: MediaApi,
    private val mediaDao: MediaDao
) {

    suspend fun searchMulti(query: String): Outcome<List<MediaItem>> {
        return apiCall { mediaApi.searchMulti(query) }
            .map { mediaResultList ->
                MediaApiMapper.mapFromMediaResultListResponse(mediaResultList)
            }
    }

    suspend fun getTrendingMedia(
        mediaType: MediaType,
        timeWindow: TimeWindow
    ): Outcome<List<MediaItem>> {
        return when (mediaType) {
            MediaType.UNDEFINED, MediaType.PERSON -> Outcome.successEmpty()
            else -> apiCall {
                mediaApi.getTrendingMedia(mediaType.toString(), timeWindow.toString())
            }.map { mediaResultList ->
                MediaApiMapper.mapFromMediaResultListResponse(
                    mediaResultList,
                    mediaType = mediaType
                )
            }
        }
    }

    suspend fun getPopularMovies(): Outcome<List<MediaItem>> {
        return apiCall { mediaApi.getPopularMovies() }
            .map { mediaResultList ->
                MediaApiMapper.mapFromMediaResultListResponse(
                    mediaResultList,
                    mediaType = MediaType.MOVIE
                )
            }
    }

    suspend fun getPopularTvSeries(): Outcome<List<MediaItem>> {
        return apiCall { mediaApi.getPopularTvSeries() }
            .map { mediaResultList ->
                MediaApiMapper.mapFromMediaResultListResponse(
                    mediaResultList,
                    mediaType = MediaType.TV
                )
            }
    }

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

    fun getMedia(id: Int): Flow<MediaEntity?> = mediaDao.getMedia(id)
}