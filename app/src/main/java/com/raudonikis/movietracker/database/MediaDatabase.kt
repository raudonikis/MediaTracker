package com.raudonikis.movietracker.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.raudonikis.movietracker.database.daos.MovieDao
import com.raudonikis.movietracker.database.daos.TvSeriesDao
import com.raudonikis.movietracker.database.entities.MovieEntity
import com.raudonikis.movietracker.database.entities.TvSeriesEntity

@Database(entities = [MovieEntity::class, TvSeriesEntity::class], version = 1)
@TypeConverters(DatabaseConverters::class)
abstract class MediaDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun tvSeriesDao(): TvSeriesDao

    companion object {
        const val DATABASE_NAME = "media_db"
    }
}