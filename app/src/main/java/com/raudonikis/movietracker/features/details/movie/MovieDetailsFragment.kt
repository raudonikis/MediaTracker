package com.raudonikis.movietracker.features.details.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.raudonikis.movietracker.R
import com.raudonikis.movietracker.api.util.MediaApiConstants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_details_local.*

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(R.layout.fragment_details_local) {

    private val arguments: MovieDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    private fun setUpViews() {
        Glide.with(image_poster)
            .load(MediaApiConstants.IMAGE_URL + arguments.movie.posterPath)
            .into(image_poster)
        text_title.text = arguments.movie.title
    }
}