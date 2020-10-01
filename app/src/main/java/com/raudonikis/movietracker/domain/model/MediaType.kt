package com.raudonikis.movietracker.domain.model

import java.util.*

enum class MediaType {
    MOVIE,
    TV,
    PERSON,
    UNDEFINED;

    override fun toString(): String {
        return when (this) {
            MOVIE -> KEY_MOVIE
            TV -> KEY_TV
            PERSON -> KEY_PERSON
            else -> KEY_UNDEFINED
        }
    }

    companion object {
        private const val KEY_MOVIE = "movie"
        private const val KEY_TV = "tv"
        private const val KEY_PERSON = "person"
        private const val KEY_UNDEFINED = "undefined"

        fun fromString(string: String): MediaType {
            return when {
                string.toLowerCase(Locale.getDefault()) == KEY_MOVIE -> MOVIE
                string.toLowerCase(Locale.getDefault()) == KEY_TV -> TV
                string.toLowerCase(Locale.getDefault()) == KEY_PERSON -> PERSON
                else -> UNDEFINED
            }
        }
    }
}