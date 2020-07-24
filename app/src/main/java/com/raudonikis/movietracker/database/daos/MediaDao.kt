package com.raudonikis.movietracker.database.daos

import androidx.room.*
import com.raudonikis.movietracker.database.entities.MediaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MediaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedia(media: MediaEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedia(media: List<MediaEntity>)

    @Query("Select * from media")
    fun getAllMedia(): Flow<List<MediaEntity>>

    @Query("SELECT * FROM media WHERE id = :id")
    fun getMedia(id: Int): Flow<MediaEntity?>

    @Delete
    suspend fun remove(media: MediaEntity)
}