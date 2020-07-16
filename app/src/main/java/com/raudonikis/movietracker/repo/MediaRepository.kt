package com.raudonikis.movietracker.repo

import com.raudonikis.movietracker.api.MediaApiMapper
import com.raudonikis.movietracker.api.MediaApi
import com.raudonikis.movietracker.model.MediaItem
import com.raudonikis.movietracker.util.Outcome
import com.raudonikis.movietracker.util.apiCall
import javax.inject.Inject

class MediaRepository @Inject constructor(private val mediaApi: MediaApi) {

    suspend fun searchMulti(query: String): Outcome<List<MediaItem>> {
        return apiCall { mediaApi.searchMulti(query) }
            .map { multiResponse ->
                MediaApiMapper.mapFromMultiSearchResponse(multiResponse)
            }
    }
}