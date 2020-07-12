package com.raudonikis.movietracker.features.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.raudonikis.movietracker.R
import com.raudonikis.movietracker.model.MovieItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel by viewModels<SearchViewModel>()
    private val movieAdapter = MovieItemAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
        setUpRecyclerView()
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.movies.observe(viewLifecycleOwner) {
            movieAdapter.submitList(it)
        }
    }

    private fun setUpRecyclerView() {
        recycler_view.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    private fun setUpListeners() {
        button_search.setOnClickListener {
            viewModel.searchMovies(edit_search.editableText.toString())
        }
    }
}