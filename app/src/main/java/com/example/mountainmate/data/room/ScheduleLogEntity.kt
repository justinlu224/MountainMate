package com.example.mountainmate.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.mountainmate.di.SCHEDULE_LOG_TABLE_NAME

@Entity(
    tableName = SCHEDULE_LOG_TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = ScheduleEntity::class,
            parentColumns = ["id"],
            childColumns = ["schedule_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ScheduleLogEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("schedule_id")
    val scheduleId: Int,
    @ColumnInfo("time")
    val time: Long,
    @ColumnInfo("longitude")
    val longitude: Double? = null,
    @ColumnInfo("latitude")
    val latitude: Double? = null,
    @ColumnInfo("description")
    val description: String
)