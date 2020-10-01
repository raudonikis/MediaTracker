package com.raudonikis.movietracker.domain.usecases.search

import com.raudonikis.movietracker.data.api.MediaApi
import com.raudonikis.movietracker.data.api.mappers.MediaApiMapper
import com.raudonikis.movietracker.domain.model.MediaItem
import com.raudonikis.movietracker.util.Outcome
import com.raudonikis.movietracker.data.api.util.apiCall
import javax.inject.Inject

class SearchMediaUseCase @Inject constructor(private val mediaApi: MediaApi) {

    suspend fun searchMulti(query: String): Outcome<List<MediaItem>> {
        return apiCall {
            mediaApi.searchMulti(
                query
            )
        }
            .map { mediaResultList ->
                MediaApiMapper.mapFromMediaResultListResponse(mediaResultList)
            }
    }
}