package com.raudonikis.movietracker.domain.usecases.discover

import com.raudonikis.movietracker.data.api.MediaApi
import com.raudonikis.movietracker.data.api.mappers.MediaApiMapper
import com.raudonikis.movietracker.domain.model.MediaItem
import com.raudonikis.movietracker.domain.model.MediaType
import com.raudonikis.movietracker.domain.model.TimeWindow
import com.raudonikis.movietracker.util.Outcome
import com.raudonikis.movietracker.data.api.util.apiCall
import javax.inject.Inject

class TrendingMediaUseCase @Inject constructor(
    private val mediaApi: MediaApi
) {

    suspend fun getTrendingTvSeries(timeWindow: TimeWindow): Outcome<List<MediaItem>> {
        return apiCall {
            mediaApi.getTrendingMedia(MediaType.TV.toString(), timeWindow.toString())
        }.map { mediaResultListResponse ->
            MediaApiMapper.mapFromMediaResultListResponse(
                mediaResultListResponse,
                mediaType = MediaType.TV
            )
        }
    }

    suspend fun getTrendingMovies(timeWindow: TimeWindow): Outcome<List<MediaItem>> {
        return apiCall {
            mediaApi.getTrendingMedia(MediaType.MOVIE.toString(), timeWindow.toString())
        }.map { mediaResultListResponse ->
            MediaApiMapper.mapFromMediaResultListResponse(
                mediaResultListResponse,
                mediaType = MediaType.MOVIE
            )
        }
    }
}