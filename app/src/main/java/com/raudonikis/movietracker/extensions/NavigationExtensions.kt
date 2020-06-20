package com.raudonikis.movietracker.extensions

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

fun Fragment.navigate(destination: NavDirections) = with(findNavController()) {
    currentDestination?.getAction(destination.actionId)
        ?.let { navigate(destination) }
}

fun Fragment.navigate(actionId: Int) = with(findNavController()) {
    currentDestination?.getAction(actionId)
        ?.let { navigate(actionId) }
}