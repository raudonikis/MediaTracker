package com.raudonikis.movietracker.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MediaItem(
    val title: String?,
    val posterPath: String?,
    val mediaType: String
) : Parcelable