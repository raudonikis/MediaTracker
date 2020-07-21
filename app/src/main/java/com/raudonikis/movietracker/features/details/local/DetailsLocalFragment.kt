package com.raudonikis.movietracker.features.details.local

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.raudonikis.movietracker.R
import com.raudonikis.movietracker.api.util.MediaApiConstants
import com.raudonikis.movietracker.features.watched.WatchedViewModel
import com.raudonikis.movietracker.model.MediaItem
import com.raudonikis.movietracker.util.hiltNavGraphViewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_details_local.*

@AndroidEntryPoint
class DetailsLocalFragment : Fragment(R.layout.fragment_details_local) {

    private val viewModel: WatchedViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        setUpListeners()
    }

    private fun setUpListeners() {
        button_remove.setOnClickListener {
            viewModel.removeFromWatchedList()
        }
    }

    private fun setUpObservers() {
        viewModel.selectedMedia.observe(viewLifecycleOwner) {
            setUpViews(it)
        }
    }

    private fun setUpViews(mediaItem: MediaItem) {
        Glide.with(image_poster)
            .load(MediaApiConstants.IMAGE_URL + mediaItem.posterPath)
            .into(image_poster)
        text_title.text = mediaItem.title
    }
}