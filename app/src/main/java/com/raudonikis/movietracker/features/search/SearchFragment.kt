package com.raudonikis.movietracker.features.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.raudonikis.movietracker.R
import com.raudonikis.movietracker.extensions.hide
import com.raudonikis.movietracker.extensions.show
import com.raudonikis.movietracker.model.MediaItemAdapter
import com.raudonikis.movietracker.util.hiltNavGraphViewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel by hiltNavGraphViewModels<SearchViewModel>(R.id.nav_graph)
    private lateinit var mediaAdapter: MediaItemAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
        setUpRecyclerView()
        setUpObservers()
        restoreViews()
    }

    private fun restoreViews() {
        edit_search.setText(viewModel.searchQuery)
    }

    private fun setUpObservers() {
        viewModel.mediaItemList.observe(viewLifecycleOwner) { outcome ->
            outcome
                .onLoading { progress_search.show() }
                .onSuccess {
                    progress_search.hide()
                    mediaAdapter.submitList(it)
                }
        }
    }

    private fun setUpRecyclerView() {
        mediaAdapter = MediaItemAdapter(viewModel)
        recycler_view.apply {
            adapter = mediaAdapter
            layoutManager = GridLayoutManager(context, SPAN_COUNT_MEDIA)
        }
    }

    private fun setUpListeners() {
        button_search.setOnClickListener {
            viewModel.searchMedia()
        }
        edit_search.doOnTextChanged { text, _, _, _ ->
            viewModel.searchQuery = text.toString()
        }
        edit_search.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_GO -> {
                    viewModel.searchMedia()
                }
            }
            true
        }
    }

    companion object {
        private const val SPAN_COUNT_MEDIA = 3
    }
}