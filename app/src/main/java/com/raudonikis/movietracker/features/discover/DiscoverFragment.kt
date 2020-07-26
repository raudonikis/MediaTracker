package com.raudonikis.movietracker.features.discover

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.raudonikis.movietracker.R
import com.raudonikis.movietracker.extensions.bindToView
import com.raudonikis.movietracker.model.MediaItem
import com.raudonikis.movietracker.util.hiltNavGraphViewModels
import com.raudonikis.movietracker.util.recyclerview.RecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_discover.*

@AndroidEntryPoint
class DiscoverFragment : Fragment(R.layout.fragment_discover) {

    private val viewModel by hiltNavGraphViewModels<DiscoverViewModel>(R.id.nav_graph)
    private val trendingMoviesAdapter =
        RecyclerAdapter<MediaItem>(R.layout.item_media_discover, { item ->
            item.bindToView(this)
        }, {
            //
        })
    private val trendingTvAdapter =
        RecyclerAdapter<MediaItem>(R.layout.item_media_discover, { item ->
            item.bindToView(this)
        }, {
            //
        })
    private val popularMoviesAdapter =
        RecyclerAdapter<MediaItem>(R.layout.item_media_discover, { item ->
            item.bindToView(this)
        }, {
            //
        })
    private val popularTvAdapter =
        RecyclerAdapter<MediaItem>(R.layout.item_media_discover, { item ->
            item.bindToView(this)
        }, {
            //
        })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerViews()
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.trendingMovies.observe(viewLifecycleOwner) {
            trendingMoviesAdapter.updateList(it)
        }
        viewModel.trendingTv.observe(viewLifecycleOwner) {
            trendingTvAdapter.updateList(it)
        }
        viewModel.popularMovies.observe(viewLifecycleOwner) {
            popularMoviesAdapter.updateList(it)
        }
        viewModel.popularTv.observe(viewLifecycleOwner) {
            popularTvAdapter.updateList(it)
        }
    }

    private fun setUpRecyclerViews() {
        recycler_trending_tv.apply {
            adapter = trendingTvAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            hasFixedSize()
        }
        recycler_trending_movies.apply {
            adapter = trendingMoviesAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            hasFixedSize()
        }
        recycler_popular_tv.apply {
            adapter = popularTvAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            hasFixedSize()
        }
        recycler_popular_movies.apply {
            adapter = popularMoviesAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            hasFixedSize()
        }
    }
}