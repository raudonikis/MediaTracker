package com.raudonikis.movietracker.data.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.raudonikis.movietracker.domain.model.MediaType

class DatabaseConverters {

    @TypeConverter
    fun fromIntList(list: List<Int>?): String {
        return when (list) {
            null -> ""
            else -> Gson().toJson(list)
        }
    }

    @TypeConverter
    fun toIntList(string: String): List<Int>? {
        return when {
            string.isBlank() -> emptyList()
            else -> Gson().fromJson(string, Array<Int>::class.java).toList()
        }
    }

    @TypeConverter
    fun fromStringList(list: List<String>?): String {
        return when (list) {
            null -> ""
            else -> Gson().toJson(list)
        }
    }

    @TypeConverter
    fun toStringList(string: String): List<String> {
        return when {
            string.isBlank() -> emptyList()
            else -> Gson().fromJson(string, Array<String>::class.java).toList()
        }
    }

    @TypeConverter
    fun fromMediaType(mediaType: MediaType): String {
        return mediaType.toString()
    }

    @TypeConverter
    fun toMediaType(string: String): MediaType {
        return MediaType.fromString(string)
    }
}