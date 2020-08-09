package com.raudonikis.movietracker.features.discover.trending

import com.raudonikis.movietracker.api.MediaApi
import com.raudonikis.movietracker.api.util.MediaApiMapper
import com.raudonikis.movietracker.model.MediaItem
import com.raudonikis.movietracker.model.MediaType
import com.raudonikis.movietracker.model.TimeWindow
import com.raudonikis.movietracker.util.Outcome
import com.raudonikis.movietracker.util.apiCall
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