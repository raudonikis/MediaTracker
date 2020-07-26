package com.raudonikis.movietracker.api.util

import com.raudonikis.movietracker.api.response.MediaResultListResponse
import com.raudonikis.movietracker.model.MediaItem
import com.raudonikis.movietracker.model.MediaType

object MediaApiMapper {

    fun mapFromMultiSearchResponse(mediaResultListResponse: MediaResultListResponse): List<MediaItem> =
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
                mediaType = MediaType.fromString(mediaResponse.mediaType)
            )
        } ?: emptyList()
}