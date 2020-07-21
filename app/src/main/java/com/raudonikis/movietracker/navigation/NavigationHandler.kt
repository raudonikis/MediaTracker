package com.raudonikis.movietracker.navigation

import androidx.navigation.NavDirections
import com.hadilq.liveevent.LiveEvent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class NavigationHandler @Inject constructor() {
    val navigationCommands = LiveEvent<NavigationCommand>()

    fun navigate(destination: NavDirections) {
        navigationCommands.postValue(NavigationCommand.To(destination))
    }

    fun navigateBack() {
        navigationCommands.postValue(NavigationCommand.Back)
    }
}