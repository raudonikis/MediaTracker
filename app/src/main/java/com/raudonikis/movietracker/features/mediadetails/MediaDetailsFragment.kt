package com.raudonikis.movietracker.features.mediadetails

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.api.load
import com.raudonikis.movietracker.R
import com.raudonikis.movietracker.api.util.MediaApiConstants
import com.raudonikis.movietracker.model.MediaItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_media_details.*

@AndroidEntryPoint
class MediaDetailsFragment : Fragment(R.layout.fragment_media_details) {

    private val args: MediaDetailsFragmentArgs by navArgs()
    private lateinit var mediaItem: MediaItem

    private val viewModel: MediaDetailsViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mediaItem = args.mediaItem
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        setUpListeners()
    }

    private fun setUpListeners() {
        button_add_watched.setOnClickListener {
            viewModel.addToWatched(mediaItem)
        }
    }

    private fun setUpViews() {
        image_poster.load(MediaApiConstants.IMAGE_URL + mediaItem.posterPath)
        text_title.text = mediaItem.title
    }
}