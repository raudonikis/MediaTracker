package com.raudonikis.movietracker.database.util

import com.raudonikis.movietracker.database.entities.MediaEntity
import com.raudonikis.movietracker.model.MediaItem

object MediaDatabaseMapper {

    fun mapFromMediaItemToEntity(mediaItem: MediaItem): MediaEntity {
        return MediaEntity(
            id = mediaItem.id,
            isAdult = mediaItem.isAdult,
            backdropPath = mediaItem.backdropPath,
            genreIds = mediaItem.genreIds,
            originalLanguage = mediaItem.originalLanguage,
            originalTitle = mediaItem.originalTitle,
            originalName = mediaItem.originalName,
            originCountry = mediaItem.originCountry,
            overview = mediaItem.overview,
            popularity = mediaItem.popularity,
            posterPath = mediaItem.posterPath,
            releaseDate = mediaItem.releaseDate,
            firstAirDate = mediaItem.firstAirDate,
            title = mediaItem.title,
            name = mediaItem.name,
            isVideo = mediaItem.isVideo,
            voteAverage = mediaItem.voteAverage,
            voteCount = mediaItem.voteCount,
            mediaType = mediaItem.mediaType
        )
    }

    fun mapFromMediaEntityToItem(mediaEntity: MediaEntity): MediaItem {
        return MediaItem(
            id = mediaEntity.id,
            isAdult = mediaEntity.isAdult,
            backdropPath = mediaEntity.backdropPath,
            genreIds = mediaEntity.genreIds,
            originalLanguage = mediaEntity.originalLanguage,
            originalTitle = mediaEntity.originalTitle,
            overview = mediaEntity.overview,
            popularity = mediaEntity.popularity,
            posterPath = mediaEntity.posterPath,
            releaseDate = mediaEntity.releaseDate,
            title = mediaEntity.title,
            isVideo = mediaEntity.isVideo,
            voteAverage = mediaEntity.voteAverage,
            voteCount = mediaEntity.voteCount,
            mediaType = mediaEntity.mediaType
        )
    }
}