package com.raudonikis.movietracker.model.response

import com.google.gson.annotations.SerializedName
import com.raudonikis.movietracker.model.response.MovieResponse

data class SearchResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<MovieResponse>?,
    @SerializedName("total_results")
    val totalResults: Int?,
    @SerializedName("total_pages")
    val totalPages: Int
)