package com.raudonikis.movietracker.api.util

import com.raudonikis.movietracker.api.response.MultiSearchResponse
import com.raudonikis.movietracker.model.MediaItem
import com.raudonikis.movietracker.model.MediaType

object MediaApiMapper {

    fun mapFromMultiSearchResponse(multiSearchResponse: MultiSearchResponse): List<MediaItem> =
        multiSearchResponse.results?.map { multiResponse ->
            MediaItem(
                id = multiResponse.id,
                isAdult = multiResponse.isAdult,
                firstAirDate = multiResponse.firstAirDate,
                releaseDate = multiResponse.releaseDate,
                genreIds = multiResponse.genreIds,
                title = multiResponse.title,
                name = multiResponse.name,
                originCountry = multiResponse.originCountry,
                originalLanguage = multiResponse.originalLanguage,
                originalName = multiResponse.originalName,
                originalTitle = multiResponse.originalTitle,
                overview = multiResponse.overview,
                popularity = multiResponse.popularity,
                posterPath = multiResponse.posterPath,
                backdropPath = multiResponse.backdropPath,
                isVideo = multiResponse.isVideo,
                voteAverage = multiResponse.voteAverage,
                voteCount = multiResponse.voteCount,
                mediaType = MediaType.fromString(multiResponse.mediaType)
            )
        } ?: emptyList()
}