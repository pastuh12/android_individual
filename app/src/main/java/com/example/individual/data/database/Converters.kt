package com.example.individual.data.database

import androidx.room.TypeConverter
import org.joda.time.DateTime


object Converters {

    @TypeConverter
    fun fromDate(date: DateTime): Long {
        return date.millis
    }

    @TypeConverter
    fun toDate(time: Long): DateTime {
        return DateTime(time)
    }
}