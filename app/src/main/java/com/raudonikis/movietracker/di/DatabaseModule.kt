package com.raudonikis.movietracker.di

import android.content.Context
import androidx.room.Room
import com.raudonikis.movietracker.database.MediaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMediaDatabase(context: Context): MediaDatabase {
        return Room.databaseBuilder(context, MediaDatabase::class.java, MediaDatabase.DATABASE_NAME)
            .build()
    }
}