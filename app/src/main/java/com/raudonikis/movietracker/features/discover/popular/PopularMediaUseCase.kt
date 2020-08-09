package com.raudonikis.movietracker.features.discover.popular

import com.raudonikis.movietracker.api.MediaApi
import com.raudonikis.movietracker.api.util.MediaApiMapper
import com.raudonikis.movietracker.model.MediaItem
import com.raudonikis.movietracker.model.MediaType
import com.raudonikis.movietracker.util.Outcome
import com.raudonikis.movietracker.util.apiCall
import javax.inject.Inject

class PopularMediaUseCase @Inject constructor(private val mediaApi: MediaApi) {

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
}