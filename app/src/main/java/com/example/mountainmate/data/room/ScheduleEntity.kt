package com.example.mountainmate.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mountainmate.di.SCHEDULE_TABLE_NAME

@Entity(tableName = SCHEDULE_TABLE_NAME)
data class ScheduleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String
)
