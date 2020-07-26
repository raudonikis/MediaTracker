package com.raudonikis.movietracker.features.movies

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raudonikis.movietracker.R
import com.raudonikis.movietracker.extensions.showIf
import com.raudonikis.movietracker.model.Movie
import com.raudonikis.movietracker.util.BindingUtils
import com.raudonikis.movietracker.util.hiltNavGraphViewModels
import com.raudonikis.movietracker.util.recyclerview.RecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movies.*

@AndroidEntryPoint
class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private val viewModel by hiltNavGraphViewModels<MoviesViewModel>(R.id.nav_graph)

    private val moviesAdapter =
        RecyclerAdapter<Movie>(
            R.layout.item_media,
            { movieItem -> BindingUtils.bindMovieToMediaItemView(movieItem, this) },
            { viewModel.onMovieSelected(this) })

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
        viewModel.filteredMovies.observe(viewLifecycleOwner) { movies ->
            moviesAdapter.updateList(movies)
            label_no_results.showIf { movies.isEmpty() }
        }
    }

    private fun setUpRecyclerView() {
        recycler_movies.apply {
            adapter = moviesAdapter
            // TODO: refactor (maybe extension function)
            layoutManager = object : GridLayoutManager(context, SPAN_COUNT_MOVIES) {
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