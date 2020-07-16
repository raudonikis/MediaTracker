package com.raudonikis.movietracker.api

import com.raudonikis.movietracker.api.response.MultiSearchResponse
import com.raudonikis.movietracker.model.MediaItem

object MediaApiMapper {

    fun mapFromMultiSearchResponse(multiSearchResponse: MultiSearchResponse): List<MediaItem> =
        multiSearchResponse.results?.map { multiResponse ->
            MediaItem(multiResponse.title, multiResponse.posterPath, multiResponse.mediaType)
        } ?: emptyList()
}