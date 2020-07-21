package com.raudonikis.movietracker.features.mediadetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.raudonikis.movietracker.R
import com.raudonikis.movietracker.api.util.MediaApiConstants
import com.raudonikis.movietracker.extensions.enableIf
import com.raudonikis.movietracker.extensions.isNull
import com.raudonikis.movietracker.features.search.SearchViewModel
import com.raudonikis.movietracker.model.MediaItem
import com.raudonikis.movietracker.util.hiltNavGraphViewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_media_details.*

@AndroidEntryPoint
class MediaDetailsFragment : Fragment(R.layout.fragment_media_details) {

    private val viewModel: SearchViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        setUpListeners()
    }

    private fun setUpObservers() {
        viewModel.selectedMediaItemSearch.observe(viewLifecycleOwner) { mediaItem ->
            setUpViews(mediaItem)
        }
        viewModel.selectedMediaItemLocal.observe(viewLifecycleOwner) { mediaItem ->
            button_add_watched.enableIf { mediaItem.isNull() }
        }
    }

    private fun setUpListeners() {
        button_add_watched.setOnClickListener {
            viewModel.addMediaItemToWatchedList()
        }
    }

    private fun setUpViews(mediaItem: MediaItem) {
        Glide.with(image_poster)
            .load(MediaApiConstants.IMAGE_URL + mediaItem.posterPath)
            .into(image_poster)
        text_title.text = mediaItem.originalTitle ?: mediaItem.title
    }
}