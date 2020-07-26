package com.raudonikis.movietracker.database.util

import com.raudonikis.movietracker.database.entities.MediaEntity
import com.raudonikis.movietracker.model.MediaItem
import com.raudonikis.movietracker.model.Movie
import com.raudonikis.movietracker.model.TvShow

object MediaDatabaseMapper {

    fun mapFromMediaItemToEntity(mediaItem: MediaItem): MediaEntity {
        return MediaEntity(
            id = mediaItem.id,
            genreIds = mediaItem.genreIds,
            originalTitle = mediaItem.originalTitle,
            originalName = mediaItem.originalName,
            overview = mediaItem.overview,
            popularity = mediaItem.popularity,
            posterPath = mediaItem.posterPath,
            title = mediaItem.title,
            name = mediaItem.name,
            voteAverage = mediaItem.voteAverage,
            mediaType = mediaItem.mediaType
        )
    }

    fun mapFromMediaEntityToItem(mediaEntity: MediaEntity): MediaItem {
        return MediaItem(
            id = mediaEntity.id,
            genreIds = mediaEntity.genreIds,
            originalTitle = mediaEntity.originalTitle,
            overview = mediaEntity.overview,
            popularity = mediaEntity.popularity,
            posterPath = mediaEntity.posterPath,
            title = mediaEntity.title,
            voteAverage = mediaEntity.voteAverage,
            mediaType = mediaEntity.mediaType
        )
    }

    fun mapFromMediaEntityToMovie(mediaEntity: MediaEntity): Movie {
        return Movie(
            id = mediaEntity.id,
            genreIds = mediaEntity.genreIds ?: emptyList(),
            title = mediaEntity.title,
            originalTitle = mediaEntity.originalTitle,
            popularity = mediaEntity.popularity,
            overview = mediaEntity.overview,
            posterPath = mediaEntity.posterPath,
            voteAverage = mediaEntity.voteAverage
        )
    }

    fun mapFromMediaEntityToTvShow(mediaEntity: MediaEntity): TvShow {
        return TvShow(
            id = mediaEntity.id,
            genreIds = mediaEntity.genreIds ?: emptyList(),
            name = mediaEntity.name,
            originalName = mediaEntity.originalName,
            popularity = mediaEntity.popularity,
            overview = mediaEntity.overview,
            posterPath = mediaEntity.posterPath,
            voteAverage = mediaEntity.voteAverage
        )
    }
}