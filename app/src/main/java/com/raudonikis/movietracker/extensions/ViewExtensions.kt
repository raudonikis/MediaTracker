package com.raudonikis.movietracker.extensions

import android.view.View

fun View.enableIf(predicate: () -> Boolean) {
    isEnabled = predicate()
}