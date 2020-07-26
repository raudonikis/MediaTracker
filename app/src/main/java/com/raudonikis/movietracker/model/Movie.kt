package com.raudonikis.movietracker.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val genreIds: List<Int> = emptyList(),
    val title: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val voteAverage: Double? = null
) : Parcelable