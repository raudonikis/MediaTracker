package com.raudonikis.movietracker.di

import android.content.Context
import androidx.room.Room
import com.raudonikis.movietracker.database.MediaDatabase
import com.raudonikis.movietracker.database.daos.MovieDao
import com.raudonikis.movietracker.database.daos.TvSeriesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMediaDatabase(@ApplicationContext appContext: Context): MediaDatabase {
        return Room.databaseBuilder(
            appContext,
            MediaDatabase::class.java,
            MediaDatabase.DATABASE_NAME
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(mediaDatabase: MediaDatabase): MovieDao = mediaDatabase.movieDao()

    @Provides
    @Singleton
    fun provideTvSeriesDao(mediaDatabase: MediaDatabase): TvSeriesDao = mediaDatabase.tvSeriesDao()
}