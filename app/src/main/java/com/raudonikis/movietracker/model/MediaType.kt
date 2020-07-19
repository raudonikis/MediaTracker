package com.raudonikis.movietracker.model

import java.util.*

enum class MediaType {
    MOVIE,
    TV,
    PERSON,
    UNDEFINED;

    companion object {
        fun fromString(string: String): MediaType {
            return when {
                string.toLowerCase(Locale.getDefault()) == "movie" -> MOVIE
                string.toLowerCase(Locale.getDefault()) == "tv" -> TV
                string.toLowerCase(Locale.getDefault()) == "person" -> PERSON
                else -> UNDEFINED
            }
        }
    }
}