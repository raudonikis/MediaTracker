package com.raudonikis.movietracker.extensions

import android.view.View
import com.bumptech.glide.Glide
import com.raudonikis.movietracker.R
import com.raudonikis.movietracker.api.util.MediaApiConstants
import com.raudonikis.movietracker.model.MediaItem
import kotlinx.android.synthetic.main.item_movie.view.*

fun MediaItem.bindToView(view: View) {
    view.image_poster.setImageResource(R.drawable.placeholder_poster)
    if (!posterPath.isNullOrBlank()) {
        Glide.with(view.image_poster)
            .load(MediaApiConstants.IMAGE_URL + posterPath)
            .into(view.image_poster)
    }
}