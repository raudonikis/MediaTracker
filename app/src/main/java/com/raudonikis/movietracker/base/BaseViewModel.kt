package com.raudonikis.movietracker.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.hadilq.liveevent.LiveEvent
import com.raudonikis.movietracker.navigation.NavigationCommand

open class BaseViewModel : ViewModel() {

    private val _navigationCommand = LiveEvent<NavigationCommand>()
    val navigationCommand: LiveData<NavigationCommand> = _navigationCommand

    fun navigate(directions: NavDirections) {
        _navigationCommand.postValue(NavigationCommand.To(directions))
    }
}