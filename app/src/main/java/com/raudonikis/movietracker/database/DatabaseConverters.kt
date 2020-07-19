package com.raudonikis.movietracker.database

import androidx.room.TypeConverter
import com.google.gson.Gson

class DatabaseConverters {

    @TypeConverter
    fun fromIntList(list: List<Int>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toIntList(string: String): List<Int> {
        return Gson().fromJson(string, Array<Int>::class.java).toList()
    }

    @TypeConverter
    fun fromStringList(list: List<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toStringList(string: String): List<String> {
        return Gson().fromJson(string, Array<String>::class.java).toList()
    }
}