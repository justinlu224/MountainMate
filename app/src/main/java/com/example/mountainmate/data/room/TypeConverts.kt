package com.example.mountainmate.data.room

import androidx.room.TypeConverter

class TypeConverts {
    @TypeConverter
    fun fromCategory(value: Category): String {
        return value.name
    }

    @TypeConverter
    fun toCategory(value: String): Category {
        return Category.valueOf(value)
    }

}