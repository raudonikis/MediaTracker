package com.raudonikis.movietracker.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.raudonikis.movietracker.navigation.NavigationHandler

class SearchViewModel @ViewModelInject constructor(
    private val navigationHandler: NavigationHandler) : ViewModel() {

    fun navigateToMyList() {
        navigationHandler.navigate(SearchFragmentDirections.actionSearchFragmentToMyListFragment())
    }
}