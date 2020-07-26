package com.raudonikis.movietracker.database.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.raudonikis.movietracker.model.MediaType

@Entity(tableName = "media")
data class MediaEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "genre_ids")
    val genreIds: List<Int>?,
    @ColumnInfo(name = "original_name")
    val originalName: String?,
    @ColumnInfo(name = "original_title")
    val originalTitle: String?,
    @ColumnInfo(name = "overview")
    val overview: String?,
    @ColumnInfo(name = "popularity")
    val popularity: Double?,
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double?,
    @ColumnInfo(name = "media_type")
    val mediaType: MediaType
)