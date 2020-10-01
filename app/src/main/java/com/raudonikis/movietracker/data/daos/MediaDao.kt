package com.raudonikis.movietracker.data.daos

import androidx.room.*
import com.raudonikis.movietracker.data.entities.MediaEntity
import com.raudonikis.movietracker.domain.model.MediaType
import kotlinx.coroutines.flow.Flow

@Dao
interface MediaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedia(media: MediaEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedia(media: List<MediaEntity>)

    @Query("SELECT * FROM media")
    fun getAllMedia(): Flow<List<MediaEntity>>

    @Query("SELECT * FROM media WHERE media_type = :mediaType")
    fun getAllMovies(mediaType: MediaType = MediaType.MOVIE): Flow<List<MediaEntity>>

    @Query("SELECT * FROM media WHERE media_type = :mediaType")
    fun getAllTvShows(mediaType: MediaType = MediaType.TV): Flow<List<MediaEntity>>

    @Query("SELECT * FROM media WHERE id = :id")
    fun getMedia(id: Int): Flow<MediaEntity?>

    @Delete
    suspend fun remove(media: MediaEntity)
}