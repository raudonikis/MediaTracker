package com.raudonikis.movietracker.api.response

import com.google.gson.annotations.SerializedName

data class MediaResultListResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<MediaResultResponse>?,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)