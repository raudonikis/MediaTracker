package com.raudonikis.movietracker.features.watched

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.raudonikis.movietracker.R
import com.raudonikis.movietracker.database.util.MediaDatabaseMapper
import com.raudonikis.movietracker.model.MediaItemAdapter
import com.raudonikis.movietracker.util.hiltNavGraphViewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_watched.*

@AndroidEntryPoint
class WatchedFragment : Fragment(R.layout.fragment_watched) {

    private val viewModel by hiltNavGraphViewModels<WatchedViewModel>(R.id.nav_graph)
    private lateinit var mediaAdapter: MediaItemAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.media.observe(viewLifecycleOwner) { media ->
            mediaAdapter.submitList(media.map { MediaDatabaseMapper.mapFromMediaEntityToItem(it) })
        }
    }

    private fun setUpRecyclerView() {
        mediaAdapter = MediaItemAdapter(viewModel)
        recycler_tv.apply {
            adapter = mediaAdapter
            layoutManager = GridLayoutManager(context, SPAN_COUNT_MEDIA)
        }
    }

    companion object {
        private const val SPAN_COUNT_MEDIA = 3
    }
}