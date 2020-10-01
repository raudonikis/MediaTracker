package com.raudonikis.movietracker.ui.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding4.widget.textChangeEvents
import com.raudonikis.movietracker.R
import com.raudonikis.movietracker.extensions.*
import com.raudonikis.movietracker.domain.model.MediaItem
import com.raudonikis.movietracker.di.util.hiltNavGraphViewModels
import com.raudonikis.movietracker.util.recyclerview.RecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel by hiltNavGraphViewModels<SearchViewModel>(R.id.nav_graph)
    private val mediaAdapter =
        RecyclerAdapter<MediaItem>(
            R.layout.item_media,
            { item -> item.bindToView(this) },
            { viewModel.onMediaItemSelected(this) })

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
                .onSuccess { mediaList ->
                    progress_search.hide()
                    mediaAdapter.updateList(mediaList)
                    text_no_results.showIf { mediaList.isEmpty() }
                }
        }
    }

    private fun setUpRecyclerView() {
        recycler_view.apply {
            adapter = mediaAdapter
            layoutManager = object : GridLayoutManager(context,
                SPAN_COUNT_MEDIA
            ) {
                override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
                    lp?.height = height / SPAN_COUNT_MEDIA
                    return true
                }
            }
        }
    }

    private fun setUpListeners() {
        edit_search.apply {
            doOnTextChanged { text, _, _, _ ->
                viewModel.searchQuery = text.toString()
            }
            textChangeEvents()
                .debounce(DEBOUNCE_TEXT_CHANGE_IN_MILLIS, TimeUnit.MILLISECONDS)
                .subscribe {
                    viewModel.searchMedia()
                }
            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_GO -> {
                        hideKeyboard()
                    }
                }
                true
            }
        }
    }

    companion object {
        private const val SPAN_COUNT_MEDIA = 2
        private const val DEBOUNCE_TEXT_CHANGE_IN_MILLIS = 500L
    }
}