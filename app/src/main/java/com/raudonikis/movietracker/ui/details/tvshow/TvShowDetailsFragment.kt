package com.raudonikis.movietracker.ui.details.tvshow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.raudonikis.movietracker.R
import com.raudonikis.movietracker.data.api.util.MediaApiConstants
import com.raudonikis.movietracker.features.details.tvshow.TvShowDetailsFragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_details_local.*

@AndroidEntryPoint
class TvShowDetailsFragment : Fragment(R.layout.fragment_details_local) {

    private val arguments: TvShowDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    private fun setUpViews() {
        Glide.with(image_poster)
            .load(MediaApiConstants.IMAGE_URL + arguments.tvShow.posterPath)
            .into(image_poster)
        text_title.text = arguments.tvShow.name
    }
}