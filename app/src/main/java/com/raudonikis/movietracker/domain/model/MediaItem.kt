package com.raudonikis.movietracker.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MediaItem(
    val id: Int,
    val isAdult: Boolean? = null,
    val firstAirDate: String? = null,
    val releaseDate: String? = null,
    val genreIds: List<Int>? = null,
    val title: String? = null,
    val name: String? = null,
    val originCountry: List<String>? = null,
    val originalLanguage: String? = null,
    val originalName: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val isVideo: Boolean? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null,
    val mediaType: MediaType
) : Parcelable