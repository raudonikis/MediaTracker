package com.raudonikis.movietracker.data.api.mappers

import com.raudonikis.movietracker.data.api.model.MediaResultListResponse
import com.raudonikis.movietracker.domain.model.MediaItem
import com.raudonikis.movietracker.domain.model.MediaType

object MediaApiMapper {

    fun mapFromMediaResultListResponse(
        mediaResultListResponse: MediaResultListResponse,
        mediaType: MediaType? = null
    ): List<MediaItem> =
        mediaResultListResponse.results?.map { mediaResponse ->
            MediaItem(
                id = mediaResponse.id,
                isAdult = mediaResponse.isAdult,
                firstAirDate = mediaResponse.firstAirDate,
                releaseDate = mediaResponse.releaseDate,
                genreIds = mediaResponse.genreIds,
                title = mediaResponse.title,
                name = mediaResponse.name,
                originCountry = mediaResponse.originCountry,
                originalLanguage = mediaResponse.originalLanguage,
                originalName = mediaResponse.originalName,
                originalTitle = mediaResponse.originalTitle,
                overview = mediaResponse.overview,
                popularity = mediaResponse.popularity,
                posterPath = mediaResponse.posterPath,
                backdropPath = mediaResponse.backdropPath,
                isVideo = mediaResponse.isVideo,
                voteAverage = mediaResponse.voteAverage,
                voteCount = mediaResponse.voteCount,
                // TODO make sure the mediaType is set
                mediaType = mediaType
                    ?: MediaType.fromString(
                        mediaResponse.mediaType
                    )
                    ?: throw Exception()
            )
        } ?: emptyList()
}