package com.raudonikis.movietracker.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShow(
    val id: Int,
    val genreIds: List<Int> = emptyList(),
    val name: String? = null,
    val originalName: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val voteAverage: Double? = null
) : Parcelable