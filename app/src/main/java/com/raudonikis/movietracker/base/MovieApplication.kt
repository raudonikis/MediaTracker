package com.raudonikis.movietracker.base

import android.app.Application
import com.raudonikis.movietracker.BuildConfig
import com.raudonikis.movietracker.di.AppComponent
import com.raudonikis.movietracker.di.DaggerAppComponent
import timber.log.Timber

class MovieApplication : Application() {

    override fun onCreate() {
        appComponent = DaggerAppComponent.factory().create(this)
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        lateinit var appComponent: AppComponent
            private set
    }
}