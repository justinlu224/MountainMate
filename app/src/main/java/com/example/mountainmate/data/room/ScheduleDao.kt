package com.example.mountainmate.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mountainmate.di.SCHEDULE_TABLE_NAME

@Dao
interface ScheduleDao {

    @Query("SELECT * FROM $SCHEDULE_TABLE_NAME")
    suspend fun getAllSchedules(): List<ScheduleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchedule(scheduleEntity: ScheduleEntity)

    @Query("DELETE FROM $SCHEDULE_TABLE_NAME WHERE id = :id")
    suspend fun deleteSchedule(id: Int)

}