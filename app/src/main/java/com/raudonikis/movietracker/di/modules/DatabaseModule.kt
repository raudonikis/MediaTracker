package com.raudonikis.movietracker.di.modules

import android.content.Context
import androidx.room.Room
import com.raudonikis.movietracker.data.MediaDatabase
import com.raudonikis.movietracker.data.daos.MediaDao
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
    fun provideMediaDao(mediaDatabase: MediaDatabase): MediaDao = mediaDatabase.mediaDao()
}