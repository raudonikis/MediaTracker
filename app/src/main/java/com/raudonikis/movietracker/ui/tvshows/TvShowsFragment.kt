package com.raudonikis.movietracker.ui.tvshows

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raudonikis.movietracker.R
import com.raudonikis.movietracker.extensions.showIf
import com.raudonikis.movietracker.domain.model.TvShow
import com.raudonikis.movietracker.util.BindingUtils
import com.raudonikis.movietracker.di.util.hiltNavGraphViewModels
import com.raudonikis.movietracker.util.recyclerview.RecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movies.edit_text_search
import kotlinx.android.synthetic.main.fragment_movies.label_no_results
import kotlinx.android.synthetic.main.fragment_tv_shows.*

@AndroidEntryPoint
class TvShowsFragment : Fragment(R.layout.fragment_tv_shows) {

    private val viewModel by hiltNavGraphViewModels<TvShowsViewModel>(R.id.nav_graph)

    private val tvShowsAdapter =
        RecyclerAdapter<TvShow>(
            R.layout.item_media,
            { tvShowItem -> BindingUtils.bindTvShowToMediaItemView(tvShowItem, this) },
            { viewModel.onTvShowSelected(this) })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restoreViews()
        setUpRecyclerView()
        setUpObservers()
        setUpListeners()
    }

    private fun restoreViews() {
        edit_text_search.setText(viewModel.searchQuery.value)
    }

    private fun setUpListeners() {
        edit_text_search.doOnTextChanged { text, _, _, _ ->
            viewModel.updateSearchQuery(text.toString())
        }
    }

    private fun setUpObservers() {
        viewModel.filteredTvShows.observe(viewLifecycleOwner) { tvShows ->
            tvShowsAdapter.updateList(tvShows)
            label_no_results.showIf { tvShows.isEmpty() }
        }
    }

    private fun setUpRecyclerView() {
        recycler_tv_shows.apply {
            adapter = tvShowsAdapter
            // TODO: refactor (maybe extension function)
            layoutManager = object : GridLayoutManager(context,
                SPAN_COUNT_MOVIES
            ) {
                override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
                    lp?.height = height / SPAN_COUNT_MOVIES
                    return true
                }
            }
        }
    }

    companion object {
        private const val SPAN_COUNT_MOVIES = 3
    }
}