package com.raudonikis.movietracker.domain.usecases.discover

import com.raudonikis.movietracker.data.api.MediaApi
import com.raudonikis.movietracker.data.api.mappers.MediaApiMapper
import com.raudonikis.movietracker.domain.model.MediaItem
import com.raudonikis.movietracker.domain.model.MediaType
import com.raudonikis.movietracker.util.Outcome
import com.raudonikis.movietracker.data.api.util.apiCall
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