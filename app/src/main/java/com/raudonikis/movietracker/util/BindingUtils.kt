package com.raudonikis.movietracker.util

import android.view.View
import com.bumptech.glide.Glide
import com.raudonikis.movietracker.R
import com.raudonikis.movietracker.api.util.MediaApiConstants
import com.raudonikis.movietracker.model.Movie
import kotlinx.android.synthetic.main.item_media.view.*

object BindingUtils {
    fun bindMovieToMediaItemView(movie: Movie, view: View) {
        view.image_poster.setImageResource(R.drawable.placeholder_poster)
        if (!movie.posterPath.isNullOrBlank()) {
            Glide.with(view.image_poster)
                .load(MediaApiConstants.IMAGE_URL + movie.posterPath)
                .into(view.image_poster)
        }
    }
}