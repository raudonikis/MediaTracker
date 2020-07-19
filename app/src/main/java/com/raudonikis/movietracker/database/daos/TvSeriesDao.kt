package com.raudonikis.movietracker.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raudonikis.movietracker.database.entities.TvSeriesEntity

@Dao
interface TvSeriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvSeries(tvSeries: TvSeriesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvSeries(tvSeries: List<TvSeriesEntity>)

    @Query("Select * from tv_series")
    fun getTvSeries(): LiveData<List<TvSeriesEntity>>
}