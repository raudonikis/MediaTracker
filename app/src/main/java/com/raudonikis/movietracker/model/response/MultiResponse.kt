package com.raudonikis.movietracker.model.response

import com.google.gson.annotations.SerializedName
import com.raudonikis.movietracker.model.MovieItem

data class MultiResponse(
    @SerializedName("adult")
    val isAdult: Boolean?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("first_air_date")
    val firstAirDate: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("known_for")
    val knownFor: List<KnownForResponse>?,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin_country")
    val originCountry: List<String>,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_name")
    val originalName: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("profile_path")
    val profilePath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("title")
    val title: String?,
    @SerializedName("video")
    val isVideo: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
) {
    fun toMovieItem(): MovieItem = MovieItem(title, posterPath)
}