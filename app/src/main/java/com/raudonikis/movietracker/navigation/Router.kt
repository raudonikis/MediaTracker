package com.raudonikis.movietracker.navigation

import com.raudonikis.movietracker.features.search.SearchFragmentDirections
import com.raudonikis.movietracker.features.watched.WatchedFragmentDirections

object Router {
    val searchFragmentToDetailsRemoteFragment =
        SearchFragmentDirections.actionSearchFragmentToDetailsRemoteFragment()
    val watchedFragmentToDetailsLocalFragment =
        WatchedFragmentDirections.actionWatchedFragmentToDetailsLocalFragment()
}