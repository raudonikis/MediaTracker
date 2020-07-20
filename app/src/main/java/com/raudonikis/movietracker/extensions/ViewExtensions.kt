package com.raudonikis.movietracker.extensions

import android.view.View

fun View.enableIf(predicate: () -> Boolean) {
    isEnabled = predicate()
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}