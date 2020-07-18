package com.raudonikis.movietracker.navigation

import com.raudonikis.movietracker.features.search.SearchFragmentDirections
import com.raudonikis.movietracker.model.MediaItem

object Router {
    fun searchFragmentToMediaDetailsFragment(mediaItem: MediaItem) =
        SearchFragmentDirections.actionSearchFragmentToMediaDetailsFragment(mediaItem)
}