package com.raudonikis.movietracker.features.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.raudonikis.movietracker.R
import com.raudonikis.movietracker.model.MediaItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel by viewModels<SearchViewModel>()
    private val mediaAdapter = MediaItemAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
        setUpRecyclerView()
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.mediaList.observe(viewLifecycleOwner) {
            mediaAdapter.submitList(it)
        }
    }

    private fun setUpRecyclerView() {
        recycler_view.apply {
            adapter = mediaAdapter
            layoutManager = GridLayoutManager(context, SPAN_COUNT_MEDIA)
        }
    }

    private fun setUpListeners() {
        button_search.setOnClickListener {
            viewModel.searchMedia(edit_search.editableText.toString())
        }
    }

    companion object {
        private const val SPAN_COUNT_MEDIA = 3
    }
}