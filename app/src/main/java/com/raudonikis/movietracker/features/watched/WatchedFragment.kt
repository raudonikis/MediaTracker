package com.raudonikis.movietracker.features.watched

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raudonikis.movietracker.R
import com.raudonikis.movietracker.extensions.bindToView
import com.raudonikis.movietracker.extensions.showIf
import com.raudonikis.movietracker.model.MediaItem
import com.raudonikis.movietracker.util.hiltNavGraphViewModels
import com.raudonikis.movietracker.util.recyclerview.RecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_watched.*

@AndroidEntryPoint
class WatchedFragment : Fragment(R.layout.fragment_watched) {

    private val viewModel by hiltNavGraphViewModels<WatchedViewModel>(R.id.nav_graph)
    private val mediaAdapter =
        RecyclerAdapter<MediaItem>(
            R.layout.item_movie,
            { item -> item.bindToView(this) },
            { viewModel.onMediaItemSelected(this) })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpObservers()
        setUpListeners()
        restoreViews()
    }

    private fun restoreViews() {
        edit_search.setText(viewModel.searchQuery.value)
    }

    private fun setUpListeners() {
        edit_search.doOnTextChanged { text, _, _, _ ->
            viewModel.updateSearchQuery(text.toString())
        }
    }

    private fun setUpObservers() {
        viewModel.filteredMedia.observe(viewLifecycleOwner) { mediaList ->
            mediaAdapter.updateList(mediaList)
            text_no_results.showIf { mediaList.isEmpty() }
        }
    }

    private fun setUpRecyclerView() {
        recycler_tv.apply {
            adapter = mediaAdapter
            layoutManager = object : GridLayoutManager(context, SPAN_COUNT_MEDIA) {
                override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
                    lp?.height = height / SPAN_COUNT_MEDIA
                    return true
                }
            }
        }
    }

    companion object {
        private const val SPAN_COUNT_MEDIA = 3
    }
}