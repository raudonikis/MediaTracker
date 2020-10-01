package com.raudonikis.movietracker.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.raudonikis.movietracker.data.daos.MediaDao
import com.raudonikis.movietracker.data.entities.MediaEntity
import com.raudonikis.movietracker.data.util.DatabaseConverters

@Database(entities = [MediaEntity::class], version = 2)
@TypeConverters(DatabaseConverters::class)
abstract class MediaDatabase : RoomDatabase() {

    abstract fun mediaDao(): MediaDao

    companion object {
        const val DATABASE_NAME = "media_db.db"
    }
}