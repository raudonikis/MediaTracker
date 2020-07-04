package com.raudonikis.movietracker.model.response

import com.google.gson.annotations.SerializedName
import com.raudonikis.movietracker.model.response.MultiResponse

data class MultiSearchResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<MultiResponse>?,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)