package com.raudonikis.movietracker.database

import com.raudonikis.movietracker.database.entities.MovieEntity
import com.raudonikis.movietracker.database.entities.TvSeriesEntity
import com.raudonikis.movietracker.model.MediaItem
import com.raudonikis.movietracker.model.MediaType

object MediaDatabaseMapper {

    fun mapFromMediaItemToMovie(media: MediaItem): MovieEntity {
        return MovieEntity(
            id = media.id,
            isAdult = media.isAdult,
            backdropPath = media.backdropPath,
            genreIds = media.genreIds,
            originalLanguage = media.originalLanguage,
            originalTitle = media.originalTitle,
            overview = media.overview,
            popularity = media.popularity,
            posterPath = media.posterPath,
            releaseDate = media.releaseDate,
            title = media.title,
            isVideo = media.isVideo,
            voteAverage = media.voteAverage,
            voteCount = media.voteCount
        )
    }

    fun mapFromMovieToMediaItem(movie: MovieEntity): MediaItem {
        return MediaItem(
            id = movie.id,
            isAdult = movie.isAdult,
            backdropPath = movie.backdropPath,
            genreIds = movie.genreIds,
            originalLanguage = movie.originalLanguage,
            originalTitle = movie.originalTitle,
            overview = movie.overview,
            popularity = movie.popularity,
            posterPath = movie.posterPath,
            releaseDate = movie.releaseDate,
            title = movie.title,
            isVideo = movie.isVideo,
            voteAverage = movie.voteAverage,
            voteCount = movie.voteCount,
            mediaType = MediaType.MOVIE
        )
    }

    fun mapFromMediaItemToTvSeries(media: MediaItem): TvSeriesEntity {
        return TvSeriesEntity(
            id = media.id,
            backdropPath = media.backdropPath,
            genreIds = media.genreIds,
            originalLanguage = media.originalLanguage,
            originalName = media.originalName,
            originCountry = media.originCountry,
            overview = media.overview,
            popularity = media.popularity,
            posterPath = media.posterPath,
            firstAirDate = media.firstAirDate,
            name = media.name,
            voteAverage = media.voteAverage,
            voteCount = media.voteCount
        )
    }

    fun mapFromTvSeriesToMediaItem(tvSeries: TvSeriesEntity): MediaItem {
        return MediaItem(
            id = tvSeries.id,
            backdropPath = tvSeries.backdropPath,
            genreIds = tvSeries.genreIds,
            originalLanguage = tvSeries.originalLanguage,
            originalName = tvSeries.originalName,
            originCountry = tvSeries.originCountry,
            overview = tvSeries.overview,
            popularity = tvSeries.popularity,
            posterPath = tvSeries.posterPath,
            firstAirDate = tvSeries.firstAirDate,
            name = tvSeries.name,
            voteAverage = tvSeries.voteAverage,
            voteCount = tvSeries.voteCount,
            mediaType = MediaType.TV
        )
    }
}