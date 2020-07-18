package com.raudonikis.movietracker.features.discover

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.raudonikis.movietracker.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscoverFragment : Fragment(R.layout.fragment_discover) {

    private val viewModel by viewModels<DiscoverViewModel>()
}