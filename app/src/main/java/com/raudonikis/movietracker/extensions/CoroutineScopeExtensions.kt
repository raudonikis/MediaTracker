package com.raudonikis.movietracker.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun CoroutineScope.io(f: suspend () -> Unit) {
    launch(Dispatchers.IO) {
        f()
    }
}

fun CoroutineScope.main(f: suspend () -> Unit) {
    launch(Dispatchers.Main) {
        f()
    }
}