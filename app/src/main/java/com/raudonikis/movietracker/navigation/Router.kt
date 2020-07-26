package com.raudonikis.movietracker.navigation

import com.raudonikis.movietracker.features.movies.MoviesFragmentDirections
import com.raudonikis.movietracker.features.search.SearchFragmentDirections
import com.raudonikis.movietracker.features.tvshows.TvShowsFragmentDirections
import com.raudonikis.movietracker.features.watched.WatchedFragmentDirections
import com.raudonikis.movietracker.model.Movie
import com.raudonikis.movietracker.model.TvShow

object Router {
    val searchFragmentToDetailsRemoteFragment =
        SearchFragmentDirections.actionSearchFragmentToDetailsRemoteFragment()
    val watchedFragmentToDetailsLocalFragment =
        WatchedFragmentDirections.actionWatchedFragmentToDetailsLocalFragment()

    fun moviesFragmentToMovieDetailsFragment(movie: Movie) =
        MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(movie)

    fun searchFragmentToMovieDetailsFragment(movie: Movie) =
        SearchFragmentDirections.actionSearchFragmentToMovieDetailsFragment(movie)
    
    fun tvShowsFragmentToTvShowDetailsFragment(tvShow: TvShow) =
        TvShowsFragmentDirections.actionTvShowsFragmentToTvShowDetailsFragment(tvShow)
}