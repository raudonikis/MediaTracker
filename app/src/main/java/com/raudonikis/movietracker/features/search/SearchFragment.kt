package com.raudonikis.movietracker.features.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.raudonikis.movietracker.R
import com.raudonikis.movietracker.model.MovieItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel by viewModels<SearchViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
    }

    private fun setUpRecyclerView() {
        val itemAdapter = MovieItemAdapter()
        recycler_view.apply { 
            adapter = itemAdapter
        }
    }

    private fun setUpListeners() {
        button_search.setOnClickListener {
            viewModel.searchMovies(edit_search.editableText.toString())
        }
    }
}