package com.raudonikis.movietracker.domain.model

enum class TimeWindow {
    DAY,
    WEEK;

    override fun toString(): String {
        return when (this) {
            DAY -> "day"
            WEEK -> "week"
        }
    }
}